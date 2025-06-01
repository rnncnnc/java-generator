package com.qinge.backend.service.Impl;

import com.qinge.backend.builder.*;
import com.qinge.backend.entity.constants.ClassDir;
import com.qinge.backend.entity.dto.BaseInfo;
import com.qinge.backend.entity.dto.Table;
import com.qinge.backend.entity.dto.Template;
import com.qinge.backend.service.BuilderService;
import com.qinge.backend.utils.DataBaseParser;
import com.qinge.backend.utils.TemplateParser;
import com.qinge.backend.utils.Tools;


import java.io.File;
import java.io.IOException;
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
    public void buildFile() {
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
        String tempDirPath = ClassDir.TEMP_DIR + File.separator + "java-" + baseInfo.getArtifactId();

        baseInfo.setTempPath(tempDirPath);

        // 创建临时目录
        Tools.createDir(tempDirPath);

        // 解析数据库
        List<Table> tableList = getTableList(baseInfo);

        // 解析模板
        Map<String, Template> templateMap = parseTemplate();

        // 构建文件
        buildClass(baseInfo, tableList, templateMap);
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

    /**
     * 解析模板
     * @return
     */
    private Map<String, Template> parseTemplate() {
        Map<String, Template> templateMap = new HashMap<>();

        // 读取模板文件
        List<String> templates = Tools.getResourcesFiles("template");

        for (String template : templates) {
            String separator = File.separator;
            String dirName = template.split(separator.equals("\\") ? "\\\\" : separator)[1].split("\\.")[0];

            // 解析模板
            Template tem;
            if (dirName.equals("xml")) {
                tem = TemplateParser.parseXMLTemplate(template);
            } else {
                tem = TemplateParser.parseClassTemplate(template);
            }

            templateMap.put(dirName, tem);
        }

        return templateMap;
    }

    /**
     * 构建所有文件
     * @param tableList
     */
    private void buildClass(BaseInfo baseInfo, List<Table> tableList, Map<String, Template> templateMap) {
        createDir(baseInfo.getTempPath());

        for (Table table : tableList) {
            getBuilder(new PojoBuilder(), baseInfo, table, templateMap, "pojo", "entity");
            getBuilder(new ServiceBuilder(), baseInfo, table, templateMap, "service", "");
            getBuilder(new MapperBuilder(), baseInfo, table, templateMap, "mapper", "");
            getBuilder(new ServiceImplBuilder(), baseInfo, table, templateMap, "Impl", "service");
            getBuilder(new ControllerBuilder(), baseInfo, table, templateMap, "controller", "");
        }

        getBuilderSingle(new BaseMapperBuilder(), baseInfo, templateMap, "BaseMapper", "mapper", "");
        getBuilderSingle(new QueryBuilder(), baseInfo, templateMap, "query", "query", "entity");
        getBuilderSingle(new SortBuilder(), baseInfo, templateMap, "sort", "query", "entity");
        getBuilderSingle(new PageBuilder(), baseInfo, templateMap, "page", "query", "entity");
        getBuilderSingle(new ResultBuilder(), baseInfo, templateMap, "result", "vo", "entity");
    }

    /**
     * 构建单个文件
     * @param basePath
     */
    private void createDir(String basePath) {
        Tools.mkdir(basePath + File.separator + ClassDir.ENTITY_DIR);
        Tools.mkdir(basePath + File.separator + ClassDir.ENTITY_POJO_DIR);
        Tools.mkdir(basePath + File.separator + ClassDir.ENTITY_QUERY_DIR);
        Tools.mkdir(basePath + File.separator + ClassDir.ENTITY_VO_DIR);
        Tools.mkdir(basePath + File.separator + ClassDir.SERVICE_DIR);
        Tools.mkdir(basePath + File.separator + ClassDir.SERVICE_IMPL_DIR);
        Tools.mkdir(basePath + File.separator + ClassDir.MAPPER_DIR);
        Tools.mkdir(basePath + File.separator + ClassDir.MAPPER_XML_DIR);
        Tools.mkdir(basePath + File.separator + ClassDir.CONTROLLER_DIR);
    }


    private void getBuilder(Builder builder, BaseInfo baseInfo, Table table, Map<String, Template> templateMap, String basePackage, String otherPackage) {

        // 生成基础路径
        String basePath = baseInfo.getTempPath() + File.separator + basePackage;

        String equalName = basePackage;
        if (!otherPackage.equals("")) {
            equalName = otherPackage + "." + basePackage;
            basePath = baseInfo.getTempPath() + File.separator + otherPackage + File.separator + basePackage;
        }

        // 生成文件路径
        String filePath = basePath + File.separator + table.getTableName() + Tools.firstToUppercase(basePackage) + ".java";

        // 生成包名
        String basePackageName = baseInfo.getGroupId() + "." + baseInfo.getArtifactId();
        String packageName = baseInfo.getGroupId() + "." + baseInfo.getArtifactId() + "." + equalName;

        builder.setTable(table);
        builder.setTemplate(templateMap.get(basePackage));
        builder.setBasePath(basePath);
        builder.setFilePath(filePath);
        builder.setPackageName(packageName);
        builder.setBasePackageName(basePackageName);

        builder.build();
    }

    private void getBuilderSingle(Builder builder, BaseInfo baseInfo, Map<String, Template> templateMap, String fileName, String basePackage, String otherPackage) {
        // 生成基础路径
        String basePath = baseInfo.getTempPath() + File.separator + basePackage;

        String equalName = basePackage;
        if (!otherPackage.equals("")) {
            equalName = otherPackage + "." + basePackage;
            basePath = baseInfo.getTempPath() + File.separator + otherPackage + File.separator + basePackage;
        }

        // 生成文件路径
        String filePath = basePath + File.separator + fileName + ".java";

        // 生成包名
        String basePackageName = baseInfo.getGroupId() + "." + baseInfo.getArtifactId();
        String packageName = baseInfo.getGroupId() + "." + baseInfo.getArtifactId() + "." + equalName;

        builder.setTable(new Table());
        builder.setTemplate(templateMap.get(fileName));
        builder.setBasePath(basePath);
        builder.setFilePath(filePath);
        builder.setPackageName(packageName);
        builder.setBasePackageName(basePackageName);

        builder.build();
    }


    public static void main(String[] args) throws IOException {
        BuilderService builderService = new BuilderServiceImpl();
        builderService.buildFile();

    }
}
