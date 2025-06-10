package com.qinge.backend.service.Impl;

import com.qinge.backend.builder.FileBuilder;
import com.qinge.backend.connector.DBConnector;
import com.qinge.backend.entity.BaseInfo;
import com.qinge.backend.entity.table.Table;
import com.qinge.backend.entity.template.Template;
import com.qinge.backend.entity.template.object.FileObject;
import com.qinge.backend.parser.database.DBParser;
import com.qinge.backend.parser.file.ObjectParser;
import com.qinge.backend.service.BuilderService;
import com.qinge.backend.utils.ClassTools;
import com.qinge.backend.utils.FileTools;
import com.qinge.backend.utils.StringTools;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.util.*;

/**
 * @Data: 2025/5/30 15:14
 * @Author: Lc
 * @Description:
 */


@Slf4j
public class BuilderServiceImpl implements BuilderService {

    /**
     * mysql构建项目
     */
    @Override
    public void buildFile(BaseInfo baseInfo) throws Exception {
        // TODO 移到controller层
        // BaseInfo baseInfo = new BaseInfo();
        // baseInfo.setDbType("postgresql");
        // baseInfo.setDbUrl("jdbc:postgresql://192.168.0.16:5432/fiction");
        // baseInfo.setUsername("root");
        // baseInfo.setPassword("315766");
        // baseInfo.setGroupId("com.qinge");
        // baseInfo.setArtifactId("playerbackend");
        // // baseInfo.setTablePrefix("tb_");
        // baseInfo.setFieldSeparator("_");

        // 基础包名
        String temPath = baseInfo.getTempPath();
        String basePackage = baseInfo.getGroupId() + "." + baseInfo.getArtifactId();

        // 创建临时目录
        FileTools.createDir(temPath);
        log.info("创建临时目录成功：" + temPath);

        // 解析数据库
        List<Table> tableList = getTableList(baseInfo);


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

        // 解析模板
        List<Template> templateList = getAllTemplate("template");

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
     * 解析数据库信息
     * @param baseInfo
     * @return
     */
    private List<Table> getTableList(BaseInfo baseInfo) throws Exception {

        String dbType = baseInfo.getDbType();

        // 获取解析器的全类名
        String connectorClassName = DBConnector.class.getName().replace("DB", StringTools.firstToUppercase(dbType.toLowerCase()));

        // 构造连接器对象
        DBConnector connector = ClassTools.buildClassByFullName(connectorClassName);
        ClassTools.setFieldValue(connector, "url", baseInfo.getDbUrl());
        ClassTools.setFieldValue(connector, "username", baseInfo.getUsername());
        ClassTools.setFieldValue(connector, "password", baseInfo.getPassword());

        // 获取数据库解析器
        String parserClassName = DBParser.class.getName().replace("DB", StringTools.firstToUppercase(dbType.toLowerCase()));

        // 构建数据库解析器对象
        DBParser parser = ClassTools.buildClassByFullName(parserClassName);
        ClassTools.setFieldValue(parser, "connector", connector);
        ClassTools.setFieldValue(parser, "tablePrefix", baseInfo.getTablePrefix());
        ClassTools.setFieldValue(parser, "fieldSeparator", baseInfo.getFieldSeparator());

        // 解析数据库
        List<Table> tableList = parser.parseTableFromDB();

        log.info("解析数据库成功" + baseInfo.getDbType());

        return tableList;
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
