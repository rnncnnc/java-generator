package com.qinge.backend.service.Impl;

import com.qinge.backend.builder.FileBuilder;
import com.qinge.backend.dto.BaseInfo;
import com.qinge.backend.dto.TemplateDto;
import com.qinge.backend.entity.common.FileObj;
import com.qinge.backend.entity.constants.ClassDir;
import com.qinge.backend.entity.table.Table;
import com.qinge.backend.entity.template.Template;
import com.qinge.backend.entity.template.object.FileObject;
import com.qinge.backend.parser.file.ObjectParser;
import com.qinge.backend.service.BuilderService;
import com.qinge.backend.utils.ClassTools;
import com.qinge.backend.utils.FileTools;
import com.qinge.backend.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Data: 2025/5/30 15:14
 * @Author: Lc
 * @Description:
 */


@Slf4j
@Service
public class BuilderServiceImpl implements BuilderService {

    /**
     * mysql构建项目
     */
    @Override
    public String buildFile(BaseInfo baseInfo) throws Exception {

        // 创建临时目录
        FileTools.createDir(baseInfo.getTempPath());
        log.info("创建临时目录成功：" + baseInfo.getTempPath());

        // 构建文件
        return buildClass(baseInfo);
    }

    /**
     * 构建文件
     * @param baseInfo
     * @throws Exception
     */
    private String buildClass(BaseInfo baseInfo) throws Exception {

        TemplateDto templateDto = baseInfo.getTemplate();

        List<Template> templateList = null;

        // 解析模板
        // 若没有模板文件名，则全部生成文件，否则，只生成指定文件
        if (StringTools.isEmpty(templateDto.getFile())) {
            templateList = getAllTemplate(ClassDir.TEMPLATE_DIR + File.separator + templateDto.getType());
        } else {
            templateList = new ArrayList<>();
            templateList.add(parseTemplate(ClassDir.TEMPLATE_DIR + File.separator + templateDto.getType() + File.separator + templateDto.getFile()));
        }

        // 生成文件的路径，只用于生成单个文件的场景
        String filePath = "";

        for (Template template : templateList) {
            // 获取文件类型
            String fileType = template.getFileType();

            // 获取类类型
            String classType = template.getClassType();

            // 获取文件构建器
            String fullClassName =  FileBuilder.class.getName();
            String prefix = fullClassName.substring(0, fullClassName.lastIndexOf("."));
            String suffix = fullClassName.substring(fullClassName.lastIndexOf(".") + 1).replace("File", StringTools.firstToUppercase(classType));
            fullClassName = prefix + "." + fileType.toLowerCase() + "." + suffix;

            FileBuilder fileBuilder = ClassTools.buildClassByFullName(fullClassName);

            // 获取模板类型
            FileObject fileObject = template.getTemplateObj();

            // 设置基础包名
            fileBuilder.setBasePackage(baseInfo.getBasePackage());
            // 设置临时文件夹
            fileBuilder.setTemPath(baseInfo.getTempPath());

            // 只需要构建一个文件
            if (classType.equals("single")) {
                // 构建文件
                filePath = fileBuilder.build(fileObject);
            } else {
                // 如果数据库信息为空，则直接生成
                if (baseInfo.getTableList() == null || baseInfo.getTableList().isEmpty()){
                    filePath = fileBuilder.build(fileObject);
                } else {
                    // 根据数据库创建多个文件
                    for (Table table : baseInfo.getTableList()) {

                        table.setBasePackage(baseInfo.getBasePackage());

                        // 将table赋值给fileBuilder
                        ClassTools.setFieldValue(fileBuilder, "table", table);

                        // 构建文件
                        filePath = fileBuilder.build(fileObject);
                    }
                }
            }

        }

        return filePath;
    }




    /**
     * 解析路径下的所有模板文件
     * @param filePath
     * @return
     * @throws IOException
     */
    private List<Template> getAllTemplate(String filePath) throws IOException {
        List<FileObj> fileObjList = FileTools.readDir(new File(filePath));
        List<Template> templateList = new ArrayList<>();

        for (FileObj fileObj : fileObjList) {
            Template template = parseTemplate(fileObj.getPath());

            templateList.add(template);
        }

        return templateList;
    }

    /**
     * 根据文件路径解析模板
     * @param fileName
     * @return
     * @throws IOException
     */
    private Template parseTemplate(String fileName) throws IOException {

        String fileStr[] = fileName.split("-");
        String fileType = fileStr[fileStr.length - 1].split("\\.")[0];

        // 获取要解析的模板的类型
        String type = StringTools.firstToUppercase(fileType);

        // 获取解析器的全类名
        String fullClassName = ObjectParser.class.getName().replace("Object", type);

        // 构建解析器对象
        ObjectParser parser = ClassTools.buildClassByFullName(fullClassName);

        // 解析模板
        Template template = parser.parseFromYml(fileName);

        log.info("解析模板成功：" + fileName);

        return template;
    }

}
