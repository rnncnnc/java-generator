package com.qinge.backend.service.Impl;

import com.qinge.backend.builder.FileBuilder;
import com.qinge.backend.dto.BaseInfo;
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
    public void buildFile(BaseInfo baseInfo) throws Exception {

        // 基础包名
        String temPath = baseInfo.getTempPath();
        String basePackage = baseInfo.getBasePackage();

        // 创建临时目录
        FileTools.createDir(temPath);
        log.info("创建临时目录成功：" + temPath);

        // 解析数据库
        List<Table> tableList = baseInfo.getTableList();

        // 构建文件
        buildClass(basePackage, temPath, tableList);
    }

    /**
     * 构建文件
     * @param basePackage
     * @param temPath
     * @param tableList
     * @throws Exception
     */
    private void buildClass(String basePackage, String temPath, List<Table> tableList) throws Exception {

        // TODO 根据分类获取不同类别的模板
        // 解析模板
        List<Template> templateList = getAllTemplate(ClassDir.TEMPLATE_DIR + File.separator + "example");

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
            fileBuilder.setBasePackage(basePackage);
            // 设置临时文件夹
            fileBuilder.setTemPath(temPath);

            // 只需要构建一个文件
            if (classType.equals("single")) {
                // 构建文件
                fileBuilder.build(fileObject);
            } else {
                // 根据数据库创建多个文件
                for (Table table : tableList) {

                    table.setBasePackage(basePackage);

                    // 将table赋值给fileBuilder
                    ClassTools.setFieldValue(fileBuilder, "table", table);

                    // 构建文件
                    fileBuilder.build(fileObject);
                }
            }

        }
    }




    /**
     * 解析路径下的所有模板文件
     * @param filePath
     * @return
     * @throws IOException
     */
    private List<Template> getAllTemplate(String filePath) throws IOException, ClassNotFoundException {
        List<String> pathList = FileTools.getResourcesFiles(filePath);
        List<Template> templateList = new ArrayList<>();

        for (String path : pathList) {
            Template template = parseTemplate(path);

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
        // 获取要解析的模板的类型
        String type = StringTools.firstToUppercase(fileName.split("-")[1].split("\\.")[0]);

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
