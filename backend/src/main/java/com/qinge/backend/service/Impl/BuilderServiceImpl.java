package com.qinge.backend.service.Impl;

import com.qinge.backend.builder.FileBuilder;
import com.qinge.backend.builder.JavaBuilder;
import com.qinge.backend.entity.constants.ClassDir;
import com.qinge.backend.entity.dto.BaseInfo;
import com.qinge.backend.entity.dto.table.Table;
import com.qinge.backend.entity.dto.template.Template;
import com.qinge.backend.entity.dto.template.object.FileObject;
import com.qinge.backend.parser.ObjectParser;
import com.qinge.backend.service.BuilderService;
import com.qinge.backend.parser.DataBaseParser;
import com.qinge.backend.utils.ClassTools;
import com.qinge.backend.utils.FileTools;
import com.qinge.backend.utils.StringTools;
import lombok.extern.slf4j.Slf4j;


import java.io.File;
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
    public void buildFile() throws Exception {
        // TODO 移到controller层
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setDbType("mysql");
        baseInfo.setDbUrl("jdbc:mysql://192.168.0.16:3306/easy_chat");
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
        log.info("创建临时目录成功：" + temPath);

        // 解析数据库
        List<Table> tableList = getTableList(baseInfo);


        // 解析模板
        List<Template> templateList = getAllTemplate("template");

        for (Template template : templateList) {
            // 获取文件类型
            String fileType = template.getFileType();

            // 获取文件构建器
            String fullClassName = JavaBuilder.class.getName().replace("Java", StringTools.firstToUppercase(fileType));
            FileBuilder fileBuilder = ClassTools.buildClassByFullName(fullClassName);

            // 获取模板类型
            FileObject fileObject = template.getTemplateObj();

            // 设置基础包名
            fileBuilder.setBasePackage(basePackage);
            // 设置临时文件夹
            fileBuilder.setTemPath(temPath);

            // 只需要构建一个文件
            if (fileType.equals("single")) {
                // 构建文件
                fileBuilder.build(fileObject);
            } else {
                // 根据数据库创建多个文件
                for (Table table : tableList) {

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
    private List<Table> getTableList(BaseInfo baseInfo) {
        List<Table> tableList = null;

        // 解析数据库信息
        if (Objects.equals(baseInfo.getDbType().toLowerCase(), "mysql")) {
            tableList = DataBaseParser.parseMySQLTable(baseInfo);
        } else if (Objects.equals(baseInfo.getDbType().toLowerCase(), "postgresql")) {
            tableList = DataBaseParser.parsePostgreSQLTable(baseInfo);
        }

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
    public static void main(String[] args) throws Exception {
        BuilderService builderService = new BuilderServiceImpl();
        builderService.buildFile();

    }
}
