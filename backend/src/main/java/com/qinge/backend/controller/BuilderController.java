package com.qinge.backend.controller;

import com.qinge.backend.dto.BaseInfo;
import com.qinge.backend.entity.constants.ClassDir;
import com.qinge.backend.dto.DateBaseInfo;
import com.qinge.backend.entity.table.Table;
import com.qinge.backend.response.Result;
import com.qinge.backend.service.BuilderService;
import com.qinge.backend.service.TableService;
import com.qinge.backend.utils.FileTools;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Data: 2025/6/3 13:05
 * @Author: Lc
 * @Description:
 */


@Slf4j
@RestController
@RequestMapping(value = "/build")
public class BuilderController {

    @Resource
    private BuilderService builderService;

    @Resource
    private TableService tableService;

    /**
     * 获取数据库表列表
     * @param baseInfo
     * @return
     */
    @Operation(summary = "获取数据库表列表", description = "根据数据库获取表列表")
    @PostMapping("/table/list")
    public Result getTableList(@RequestBody DateBaseInfo baseInfo) throws Exception {
        List<Table> tableList = tableService.getTableList(baseInfo);


        return Result.success(tableList);
    }


    /**
     * 预览
     * @param baseInfo
     * @return
     */
    @Operation(summary = "预览", description = "根据数据库构建java代码")
    @PostMapping("/preview")
    public Result preview(@RequestBody BaseInfo baseInfo) throws Exception {

        log.info(baseInfo.toString());
        String[] strs = baseInfo.getBasePackage().split("\\.");
        String artifactId = strs[strs.length - 1];


        // 创建临时目录
        String temPath = ClassDir.TEMP_DIR + File.separator + "java-" + artifactId;
        baseInfo.setTempPath(temPath);
        log.info(baseInfo.toString());

        String filePath = builderService.buildFile(baseInfo);

        log.info(filePath);

        try (InputStream inputStream = new FileInputStream(filePath)) {

            return Result.success(new String(inputStream.readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取数据
     * @return
     */
    @Operation(summary = "java代码生成器", description = "根据数据库构建java代码")
    @PostMapping("/builder")
    public void builder(@RequestBody BaseInfo baseInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String[] strs = baseInfo.getBasePackage().split("\\.");
        String artifactId = strs[strs.length - 1];


        // 创建临时目录
        String temPath = ClassDir.TEMP_DIR + File.separator + "java-" + artifactId;
        baseInfo.setTempPath(temPath);
        log.info(baseInfo.toString());

        builderService.buildFile(baseInfo);

        String zipPath = FileTools.zipFiles(temPath, artifactId);

        // 压缩包路径
        File file = new File(zipPath);

        // 设置响应的编码集
        response.setCharacterEncoding("utf-8");
        InputStream is = null;
        OutputStream os = null;

        try{
            // 文件大小
            long fSize = file.length();

            // 设置返回类型
            response.setContentType("application/x-download");

            // 浏览器下载时显示的文件名
            String fileName = URLEncoder.encode(file.getName(), "utf-8");
            // 标准编码格式（支持现代浏览器）
            String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
            // 兼容不同浏览器的文件名设置
            response.addHeader("Content-Disposition",
                    "attachment; filename*=UTF-8''" + encodedFileName +
                            "; filename=\"" + encodedFileName + "\"");  // 旧版浏览器兼容


            // 支持分片下载的大小
            response.setHeader("Accept-Range", "bytes");


            // 文件大小 自定义文件头
            response.setHeader("fSize", String.valueOf(fSize));
            // 文件名
            response.setHeader("fName", fileName);

            // 当前位置
            long pos = 0;
            // 末尾位置
            long last = fSize - 1;
            // 已发送数量
            long sum = 0;

            // 前端是否请求分片下载
            if (null != request.getHeader("Range")) {
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

                // 当前需要传输的文件范围
                String numRange = request.getHeader("Range").replaceAll("bytes=", "");
                String[] strRange = numRange.split("-");

                // bytes=100-200           /    bytes=100-
                if (strRange.length == 2) {
                    pos = Long.parseLong(strRange[0].trim());
                    last = Long.parseLong(strRange[1].trim());

                    if (last > fSize - 1) {
                        last = fSize - 1;
                    }
                } else {
                    pos = Long.parseLong(numRange.replaceAll("-", "").trim());
                }
            }

            // 总共需要读取的长度
            long rangeLength = last - pos + 1;

            // 设置文件范围
            String contentRange = new StringBuffer("bytes ").append(pos).append("-").append(last).append("/").append(fSize).toString();
            response.setHeader("Content-Range", contentRange);
            // 此次上传文件的大小
            response.setHeader("Content-Length", String.valueOf(rangeLength));

            os = new BufferedOutputStream(response.getOutputStream());
            is = new BufferedInputStream(new FileInputStream(file));

            // 跳到pos位置后开始读取文件
            is.skip(pos);

            byte[] buffer = new byte[1024];
            int length = 0;

            // 如果已发送数量小于本次总共需要发送的数量，则发送
            while (sum < rangeLength) {
                // 此次读取的数据长度
                length = is.read(buffer, 0, Math.min((int) (rangeLength - sum), buffer.length));
                sum += length;

                // 发送数据
                os.write(buffer, 0, length);
            }

            System.out.println("下载完成");

            // 下载完成后删除临时文件
            file.delete();
            FileTools.deleteDirectory(new File(temPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
    }
}
