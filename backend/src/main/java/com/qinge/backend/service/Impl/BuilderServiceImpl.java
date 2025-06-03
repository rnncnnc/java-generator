package com.qinge.backend.service.Impl;

import com.qinge.backend.entity.constants.ClassDir;
import com.qinge.backend.entity.dto.BaseInfo;
import com.qinge.backend.entity.dto.table.Table;
import com.qinge.backend.factory.ParserFactory;
import com.qinge.backend.parser.JavaParser;
import com.qinge.backend.parser.Parser;
import com.qinge.backend.parser.TemplateParser;
import com.qinge.backend.service.BuilderService;
import com.qinge.backend.parser.DataBaseParser;
import com.qinge.backend.utils.FileTools;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @Data: 2025/5/30 15:14
 * @Author: Lc
 * @Description:
 */


public class BuilderServiceImpl implements BuilderService {

    /**
     * mysql构建项目
     */
    @Override
    public void buildFile() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // TODO 移到controller层
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setDbType("mysql");
        baseInfo.setDbUrl("jdbc:mysql://gpa.bakistrim.site:3306/easy_chat");
        baseInfo.setUsername("root");
        baseInfo.setPassword("315766");
        baseInfo.setGroupId("com.qinge");
        baseInfo.setArtifactId("backend");
        baseInfo.setTablePrefix("tb_");
        baseInfo.setFieldSeparator("_");

        // 创建临时目录
        String temPath = ClassDir.TEMP_DIR + File.separator + "java-" + baseInfo.getArtifactId();

        String basePackage = baseInfo.getGroupId() + "." + baseInfo.getArtifactId();

        baseInfo.setTempPath(temPath);

        // 创建临时目录
        FileTools.createDir(temPath);

        // 解析数据库
        List<Table> tableList = getTableList(baseInfo);

        // TODO 读取JSON文件

        Map<String, Object> templateMap = TemplateParser.parseToTemplate("template//template.json");

        String fileType = (String) templateMap.get("fileType");
        if (fileType.equals("java")) {
            Parser parser = ParserFactory.buildParser(fileType);
            parser.parseTemplate((Map<String, Object>) templateMap.get("classObj"));
        }


        //
        // // 解析模板
        // Map<String, Template> templateMap = parseTemplate();
        //
        // // 构建文件
        // buildClass(baseInfo, tableList, templateMap);
    }

    /**
     * 解析数据库信息
     * @param baseInfo
     * @return
     */
    private List<Table> getTableList(BaseInfo baseInfo) {
        List<Table> tableList = null;

        // 解析数据库信息
        if (Objects.equals(baseInfo.getDbType(), "mysql")) {
            tableList = DataBaseParser.parseMySQLTable(baseInfo);
        } else if (Objects.equals(baseInfo.getDbType(), "postgresql")) {
            tableList = DataBaseParser.parsePostgreSQLTable(baseInfo);
        }

        return tableList;
    }
    //
    // /**
    //  * 解析模板
    //  * @return
    //  */
    // private Map<String, Template> parseTemplate() {
    //     Map<String, Template> templateMap = new HashMap<>();
    //
    //     // 读取模板文件
    //     List<String> templates = FileTools.getResourcesFiles("template");
    //
    //     for (String template : templates) {
    //         String separator = File.separator;
    //         String dirName = template.split(separator.equals("\\") ? "\\\\" : separator)[1].split("\\.")[0];
    //
    //         // 解析模板
    //         Template tem;
    //         if (dirName.equals("xml")) {
    //             tem = TemplateParser.parseXMLTemplate(template);
    //         } else {
    //             tem = TemplateParser.parseClassTemplate(template);
    //         }
    //
    //         templateMap.put(dirName, tem);
    //     }
    //
    //     return templateMap;
    // }
    //
    // /**
    //  * 构建所有文件
    //  * @param tableList
    //  */
    // private void buildClass(BaseInfo baseInfo, List<Table> tableList, Map<String, Template> templateMap) {
    //     createDir(baseInfo.getTempPath());
    //
    //     for (Table table : tableList) {
    //         getBuilder(new PojoBuilder(), baseInfo, table, templateMap, "pojo", "entity");
    //         getBuilder(new ServiceBuilder(), baseInfo, table, templateMap, "service", "");
    //         getBuilder(new MapperBuilder(), baseInfo, table, templateMap, "mapper", "");
    //         getBuilder(new ServiceImplBuilder(), baseInfo, table, templateMap, "Impl", "service");
    //         getBuilder(new ControllerBuilder(), baseInfo, table, templateMap, "controller", "");
    //     }
    //
    //     getBuilderSingle(new BaseMapperBuilder(), baseInfo, templateMap, "BaseMapper", "mapper", "");
    //     getBuilderSingle(new QueryBuilder(), baseInfo, templateMap, "query", "query", "entity");
    //     getBuilderSingle(new SortBuilder(), baseInfo, templateMap, "sort", "query", "entity");
    //     getBuilderSingle(new PageBuilder(), baseInfo, templateMap, "page", "query", "entity");
    //     getBuilderSingle(new ResultBuilder(), baseInfo, templateMap, "result", "vo", "entity");
    // }
    //
    // /**
    //  * 构建单个文件
    //  * @param basePath
    //  */
    // private void createDir(String basePath) {
    //     FileTools.mkdir(basePath + File.separator + ClassDir.ENTITY_DIR);
    //     FileTools.mkdir(basePath + File.separator + ClassDir.ENTITY_POJO_DIR);
    //     FileTools.mkdir(basePath + File.separator + ClassDir.ENTITY_QUERY_DIR);
    //     FileTools.mkdir(basePath + File.separator + ClassDir.ENTITY_VO_DIR);
    //     FileTools.mkdir(basePath + File.separator + ClassDir.SERVICE_DIR);
    //     FileTools.mkdir(basePath + File.separator + ClassDir.SERVICE_IMPL_DIR);
    //     FileTools.mkdir(basePath + File.separator + ClassDir.MAPPER_DIR);
    //     FileTools.mkdir(basePath + File.separator + ClassDir.MAPPER_XML_DIR);
    //     FileTools.mkdir(basePath + File.separator + ClassDir.CONTROLLER_DIR);
    // }
    //
    //
    // private void getBuilder(Builder builder, BaseInfo baseInfo, Table table, Map<String, Template> templateMap, String basePackage, String otherPackage) {
    //
    //     // 生成基础路径
    //     String basePath = baseInfo.getTempPath() + File.separator + basePackage;
    //
    //     String equalName = basePackage;
    //     if (!otherPackage.equals("")) {
    //         equalName = otherPackage + "." + basePackage;
    //         basePath = baseInfo.getTempPath() + File.separator + otherPackage + File.separator + basePackage;
    //     }
    //
    //     // 生成文件路径
    //     String filePath = basePath + File.separator + table.getTableName() + StringTools.firstToUppercase(basePackage) + ".java";
    //
    //     // 生成包名
    //     String basePackageName = baseInfo.getGroupId() + "." + baseInfo.getArtifactId();
    //     String packageName = baseInfo.getGroupId() + "." + baseInfo.getArtifactId() + "." + equalName;
    //
    //     builder.setTable(table);
    //     builder.setTemplate(templateMap.get(basePackage));
    //     builder.setBasePath(basePath);
    //     builder.setFilePath(filePath);
    //     builder.setPackageName(packageName);
    //     builder.setBasePackageName(basePackageName);
    //
    //     builder.build();
    // }
    //
    // private void getBuilderSingle(Builder builder, BaseInfo baseInfo, Map<String, Template> templateMap, String fileName, String basePackage, String otherPackage) {
    //     // 生成基础路径
    //     String basePath = baseInfo.getTempPath() + File.separator + basePackage;
    //
    //     String equalName = basePackage;
    //     if (!otherPackage.equals("")) {
    //         equalName = otherPackage + "." + basePackage;
    //         basePath = baseInfo.getTempPath() + File.separator + otherPackage + File.separator + basePackage;
    //     }
    //
    //     // 生成文件路径
    //     String filePath = basePath + File.separator + fileName + ".java";
    //
    //     // 生成包名
    //     String basePackageName = baseInfo.getGroupId() + "." + baseInfo.getArtifactId();
    //     String packageName = baseInfo.getGroupId() + "." + baseInfo.getArtifactId() + "." + equalName;
    //
    //     builder.setTable(new Table());
    //     builder.setTemplate(templateMap.get(fileName));
    //     builder.setBasePath(basePath);
    //     builder.setFilePath(filePath);
    //     builder.setPackageName(packageName);
    //     builder.setBasePackageName(basePackageName);
    //
    //     builder.build();
    // }
    //
    //
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        BuilderService builderService = new BuilderServiceImpl();
        builderService.buildFile();

    }
}
