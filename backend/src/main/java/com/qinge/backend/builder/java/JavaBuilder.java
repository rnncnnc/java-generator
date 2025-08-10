package com.qinge.backend.builder.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qinge.backend.builder.FileBuilder;
import com.qinge.backend.entity.table.Table;
import com.qinge.backend.entity.template.object.FileObject;
import com.qinge.backend.entity.template.object.java.*;
import com.qinge.backend.entity.enums.ClassTypes;
import com.qinge.backend.utils.ClassTools;
import com.qinge.backend.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @Data: 2025/6/5 12:23
 * @Author: Lc
 * @Description:
 */


@Slf4j
public abstract class JavaBuilder extends FileBuilder {

    // 数据库的表信息
    protected Table table;

    /**
     * 构建文件
     * @param fileObj
     * @throws IOException
     */
    @Override
    public String build(FileObject fileObj) throws IOException {
        JavaClass javaClass = (JavaClass) fileObj;

        // 写入内容之前进行的操作
        if (table != null) {
            javaClass = beforeWrite(javaClass);
        }

        // 替换公共变量
        JavaClass javaClassUsed = ClassTools.deepCopy(javaClass);
        javaClass = replaceKeyword(javaClassUsed, commonVariables);

        // 拼接文件路径
        String filePath = temPath + File.separator + javaClass.getPackageName().replace(".", File.separator) + File.separator + javaClass.getName() + ".java";

        File parentDir = new File(filePath).getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) { // 递归创建多级目录
            throw new IOException("父目录创建失败: " + parentDir.getAbsolutePath());
        }

        // 写入内容
        String content = writeContent(javaClass);

        // 写入内容之后进行的操作
        content = afterWrite(content);

        // 3. 写入文件（自动创建文件，若已存在则覆盖）
        Files.write(Paths.get(filePath), content.getBytes());
        log.info("写入文件成功: " + javaClass.getName());

        return filePath;
    }

    /**
     * 写入内容之前要进行的操作
     * @param javaClass
     */
    protected JavaClass beforeWrite(JavaClass javaClass) throws JsonProcessingException {
        // 深拷贝类
        // 替换table关键字
        JavaClass javaClassUsed = null;
        javaClassUsed = ClassTools.deepCopy(javaClass);
        JavaClass javaClassReplaceTable = replaceKeyword(javaClassUsed, table);

        // 替换field关键字
        javaClassUsed = ClassTools.deepCopy(javaClassReplaceTable);
        JavaClass javaClassReplaceField = replaceKeyword(javaClassUsed, table.getFields().get(0));

        // 替换index关键字
        javaClassUsed = ClassTools.deepCopy(javaClassReplaceField);
        JavaClass javaClassReplaceIndex = replaceKeyword(javaClassUsed, table.getIndexs().get(0));

        // 返回替换关键词后的类
        return javaClassReplaceIndex;
    };

    /**
     * 写入内容之后要进行的操作
     * @param content
     * @return
     */
    protected String afterWrite(String content) {
        return content;
    };

    /**
     * 写入内容
     * @param javaClass
     * @return
     */
    protected String writeContent(JavaClass javaClass) {
        StringBuilder builder = new StringBuilder();

        // 写入 需要导入的变量
        writeImport(builder, javaClass);

        // 写入注释
        writeComment(builder, javaClass.getComment(), "");

        builder.append("\n");
        builder.append("\n");

        // 写入注解
        writeAnnotation(builder, javaClass.getAnnotations(), "", "\n");

        if (StringTools.isEmpty(javaClass.getName()) || StringTools.isEmpty(javaClass.getType())) {
            throw new RuntimeException("模板错误,缺失数据");
        }

        // 写入权限修饰符
        writePermission(builder, javaClass.getPermission());

        // 写入类开头
        builder.append(javaClass.getType()).append(" ").append(javaClass.getName());

        writeGeneric(builder, javaClass.getGenerics());

        if (javaClass.getExtendsClass() != null && javaClass.getExtendsClass().getClassName() != null) {
            builder.append(" extends ").append(javaClass.getExtendsClass().getClassName());

            // 写入泛型参数
            writeGenericParams(builder, javaClass.getExtendsClass().getGenericParams());

        }

        // 写入接口列表
        if (!CollectionUtils.isEmpty(javaClass.getImplementsList())) {

            builder.append(" implements ");

            for (ClassInfo classInfo : javaClass.getImplementsList()) {
                if (StringTools.isEmpty(classInfo.getClassName())) {
                    throw new RuntimeException("模板错误,缺失数据");
                }

                builder.append(classInfo.getClassName());

                // 写入泛型参数
                writeGenericParams(builder, classInfo.getGenericParams());


                if (javaClass.getImplementsList().indexOf(classInfo)!= javaClass.getImplementsList().size() - 1) {
                    builder.append(", ");
                }
            }
        }

        builder.append(" ");

        // 写入异常
        writeException(builder, javaClass.getExceptions());

        builder.append(" {\n");

        // 写入变量
        writeVariable(builder, javaClass.getVariables());

        // 写入方法
        writeMethod(builder, javaClass.getMethods());

        builder.append("\n");
        builder.append("\n");

        // 写入构造方法
        if (StringTools.isTrue(javaClass.getConstruct())) {
            writeConstructor(builder, javaClass.getName(), javaClass.getVariables());
        }

        // 写入getter和setter方法
        if (StringTools.isTrue(javaClass.getGetAndSet())) {
            writeGetterAndSetter(builder, javaClass.getVariables());
        }

        // 类结尾
        builder.append("}");

        return builder.toString();
    }

    /**
     * 写入权限修饰符
     * @param builder
     * @param permission
     */
    protected void writePermission(StringBuilder builder, String permission) {
        if (!StringTools.isEmpty(permission)) {
            builder.append(permission)
                    .append(" ");
        }
    }

    /**
     * 写入异常
     * @param exceptionList
     */
    protected void writeException(StringBuilder builder, List<ClassInfo> exceptionList) {
        if (!CollectionUtils.isEmpty(exceptionList)) {
            builder.append("throws ");

            for (ClassInfo exception : exceptionList) {
                if (StringTools.isEmpty(exception.getClassName())) {
                    throw new RuntimeException("模板错误,缺失数据");
                }
                builder.append(exception.getClassName());
                if (exceptionList.indexOf(exception) != exceptionList.size() - 1) {
                    builder.append(", ");
                }

            }
        }
    }

    /**
     * 写入classInfo
     * @param builder
     * @param classInfo
     */
    protected void writeClassInfo(StringBuilder builder, ClassInfo classInfo) {

        if (StringTools.isEmpty(classInfo.getClassName())) {
            throw new RuntimeException("模板错误,缺失数据");
        }

        builder.append(classInfo.getClassName());

        if (!CollectionUtils.isEmpty(classInfo.getGenericParams())) {
            writeGenericParams(builder, classInfo.getGenericParams());
        }
    }

    /**
     * 写入泛型参数
     * @param builder
     * @param genericParamList
     */
    protected void writeGenericParams(StringBuilder builder, List<GenericParam> genericParamList) {
        if (!CollectionUtils.isEmpty(genericParamList)) {

            builder.append("<");

            for (GenericParam genericParam : genericParamList) {
                if (StringTools.isEmpty(genericParam.getName())) {
                    throw new RuntimeException("模板错误,缺失数据");
                }

                builder.append(genericParam.getName());

                if (genericParamList.indexOf(genericParam) != genericParamList.size() - 1) {
                    builder.append(", ");
                }
            }

            builder.append(">");

        }
    }

    /**
     * 写入泛型
     * @param builder
     * @param genericList
     */
    protected void writeGeneric(StringBuilder builder, List<Generic> genericList) {
        // 写入泛型
        if (!CollectionUtils.isEmpty(genericList)) {
            builder.append("<");
            for (Generic generic : genericList) {
                if (StringTools.isEmpty(generic.getName())) {
                    throw new RuntimeException("模板错误,缺失数据");
                }

                // 写入泛型名称
                builder.append(generic.getName());

                String type = generic.getType();
                // 如果泛型所属类型存在
                if (type != null && !type.isEmpty()) {
                    builder.append(" extends ")
                            .append(type);
                }

                // 如果不是最后一个泛型，添加逗号
                if (genericList.indexOf(generic) != genericList.size() - 1) {
                    builder.append(", ");
                }

            }

            builder.append("> ");

        }
    }

    /**
     * 写入注解
     * @param builder
     * @param annotationList
     */
    protected void writeAnnotation(StringBuilder builder, List<Annotation> annotationList, String prefix,  String split) {
        if (CollectionUtils.isEmpty(annotationList)) {
            return;
        }

        // 写入注解
        for (Annotation annotation : annotationList) {
            if (StringTools.isEmpty(annotation.getName())) {
                throw new RuntimeException("模板错误,缺失数据");
            }
            builder.append(prefix).append("@").append(annotation.getName());

            // 如果注解有参数，写入参数
            if (!CollectionUtils.isEmpty(annotation.getParams())) {
                builder.append("(");
                for (AnnotationParam annotationParam : annotation.getParams()) {

                    // 如果key为空，直接写入值，否则写入key和值
                    if (!StringTools.isEmpty(annotationParam.getKey())) {
                        builder.append(annotationParam.getKey()).append(" = ");
                    }

                    // 如果值是true或false，直接写入，否则加上双引号
                    String value = annotationParam.getValue();
                    if (value.equals("true") || value.equals("false")) {
                        builder.append(value);
                    } else {
                        builder.append("\"").append(value).append("\"");
                    }

                    if (annotation.getParams().indexOf(annotationParam)!= annotation.getParams().size() - 1) {
                        builder.append(", ");
                    }
                }
                builder.append(")")
                        .append(split);
            } else {
                builder.append(split);
            }
        }
    }


    /**
     * 写入注释
     * @param builder
     * @param commentList
     */
    protected void writeComment(StringBuilder builder, List<String> commentList, String prefix) {
        if (CollectionUtils.isEmpty(commentList)) {
            return;
        }

        builder.append("\n");
        builder.append("\n");

        for (String comment : commentList) {
            builder.append(prefix).append(comment).append("\n");
        }
    }


    /**
     * 写入变量
     * @param builder
     * @param variableList
     */
    protected void writeVariable(StringBuilder builder, List<Variable> variableList) {
        if (CollectionUtils.isEmpty(variableList)) {
            return;
        }

        for (Variable variable : variableList) {

            if (StringTools.isEmpty(variable.getName()) || StringTools.isEmpty(variable.getType().getClassName())) {
                throw new RuntimeException("模板错误,缺失数据");
            }

            // 写入注释
            writeComment(builder, variable.getComment(), "\t");

            // 写入注解
            writeAnnotation(builder, variable.getAnnotations(), "\t", "\n");

            // 写入变量
            builder.append("\t");

            // 写入权限修饰符
            writePermission(builder, variable.getPermission());

            // 写入变量类型
            writeClassInfo(builder, variable.getType());

            builder.append(" ")
                    .append(variable.getName())
                    .append(";\n");
        }
    }

    /**
     * 写入方法
     * @param builder
     * @param methodList
     */
    protected void writeMethod(StringBuilder builder, List<Method> methodList) {
        if (CollectionUtils.isEmpty(methodList)) {
            return;
        }

        for (Method method : methodList) {
            if (StringTools.isEmpty(method.getName()) || StringTools.isEmpty(method.getReturnType().getClassName())) {
                throw new RuntimeException("模板错误,缺失数据");
            }

            // 写入注释
            writeComment(builder, method.getComment(), "\t");

            // 写入注解
            writeAnnotation(builder, method.getAnnotations(), "\t", "\n");

            // 写入方法
            builder.append("\t");

            // 写入权限修饰符
            writePermission(builder, method.getPermission());

            // 写入泛型
            writeGeneric(builder, method.getGenerics());

            // 写入方法返回值的泛型
            writeClassInfo(builder, method.getReturnType());

            // 写入方法名
            builder.append(" ").append(method.getName()).append("(");

            // 写入参数
            if (!CollectionUtils.isEmpty(method.getParameters())) {

                for (MethodParameter methodParameter : method.getParameters()) {

                    if (StringTools.isEmpty(methodParameter.getName()) || StringTools.isEmpty(methodParameter.getType().getClassName())) {
                        throw new RuntimeException("模板错误,缺失数据");
                    }

                    if (!CollectionUtils.isEmpty(methodParameter.getAnnotations())) {
                        // 写入注解
                        writeAnnotation(builder, methodParameter.getAnnotations(), "", " ");
                    }

                    // 写入参数类型
                    writeClassInfo(builder, methodParameter.getType());

                    // 写入参数名
                    builder.append(" ").append(methodParameter.getName());

                    if (method.getParameters().indexOf(methodParameter) != method.getParameters().size() - 1) {
                        builder.append(", ");
                    }
                }
            }
            builder.append(")");

            // 写入异常
            writeException(builder, method.getExceptions());


            // 写入方法体
            if (method.getBodyInfo() != null && !CollectionUtils.isEmpty(method.getBodyInfo().getBody())) {
                builder.append(" {\n");

                for (String body : method.getBodyInfo().getBody()) {
                    builder.append("\t\t").append(body).append("\n");
                }

                builder.append("\t}\n");
            } else {
                builder.append(";\n");
            }
        }
    }

    /**
     * 写入 需要导入的变量
     * @param builder
     * @param javaClass
     */
    protected void writeImport(StringBuilder builder, JavaClass javaClass) {

        // 设置包名
        String packageName = basePackage + "." + javaClass.getPackageName();
        builder.append("package " + packageName + ";\n");

        // 存储创建的类
        Set<String> baseClass = new HashSet<>();

        // 存储其他jar包的类
        Set<String> otherClass = new HashSet<>();

        // 解析继承类
        parseClassInfo(baseClass, otherClass, javaClass.getExtendsClass());

        // 写入接口
        parseClassInfoList(baseClass, otherClass, javaClass.getImplementsList());

        // 获取泛型中需要导入的类
        parseGenericList(baseClass, otherClass, javaClass.getGenerics());

        // 获取注解中需要导入的类
        parseAnnotationList(baseClass, otherClass, javaClass.getAnnotations());

        // 解析异常
        parseClassInfoList(baseClass, otherClass, javaClass.getExceptions());


        // 获取变量中需要导入的类
        if (!CollectionUtils.isEmpty(javaClass.getVariables())) {
            for (Variable variable : javaClass.getVariables()) {

                // 解析变量类型
                parseClassInfo(baseClass, otherClass, variable.getType());

                // 获取变量上注解中需要导入的类
                parseAnnotationList(baseClass, otherClass, variable.getAnnotations());

                // 获取泛型中需要导入的类
                parseGenericParamList(baseClass, otherClass, variable.getType().getGenericParams());

            }
        }

        // 获取方法中需要导入的类
        if (!CollectionUtils.isEmpty(javaClass.getMethods())) {
            for (Method method : javaClass.getMethods()) {
                // 解析方法返回值
                parseClassInfo(baseClass, otherClass, method.getReturnType());

                // 获取变量上注解中需要导入的类
                parseAnnotationList(baseClass, otherClass, method.getAnnotations());

                // 获取泛型中需要导入的类
                parseGenericList(baseClass, otherClass, method.getGenerics());

                // 解析异常
                parseClassInfoList(baseClass, otherClass, method.getExceptions());

                // 获取参数中需要导入的类
                if (!CollectionUtils.isEmpty(method.getParameters())) {
                    for (MethodParameter parameter : method.getParameters()) {

                        // 解析方法参数
                        parseClassInfo(baseClass, otherClass, parameter.getType());

                        // 获取参数注解需要导入的类
                        parseAnnotationList(baseClass, otherClass, parameter.getAnnotations());
                    }
                }

                // 获取方法体内需要导入的类
                if (method.getBodyInfo() != null) {
                    parseClassInfoList(baseClass, otherClass, method.getBodyInfo().getRequire());
                }
            }
        }

        Set<String> importList = analyseClass(otherClass);

        for (String aClass : baseClass) {
            builder.append(aClass);
        }

        builder.append("\n");

        for (String str : importList) {
            builder.append(str);
        }
    }

    /**
     * 解析annotation 注解列表
     * @param baseClass
     * @param otherClass
     * @param annotationList
     */
    protected void parseAnnotationList(Set<String> baseClass, Set<String> otherClass, List<Annotation> annotationList) {
        if (!CollectionUtils.isEmpty(annotationList)) {
            for (Annotation annotation : annotationList) {
                otherClass.add(annotation.getName());
            }
        }
    }

    /**
     * 解析classInfo
     * @param baseClass
     * @param otherClass
     * @param classInfo
     */
    protected void parseClassInfo(Set<String> baseClass, Set<String> otherClass, ClassInfo classInfo) {
        if (classInfo == null || StringTools.isEmpty(classInfo.getClassName())) {
            return;
        }

        if (StringTools.isEmpty(classInfo.getPackageName())) {
            // 否则,需要从环境中导入
            otherClass.add(classInfo.getClassName());
        } else if (!StringTools.isTrue(classInfo.getGeneric())){
            // 如果有包名 则是创建的类
            baseClass.add("import " + basePackage + "." + classInfo.getPackageName() + "." + classInfo.getClassName() + ";\n");
        }

        // 获取泛型中需要导入的类
        parseGenericParamList(baseClass, otherClass, classInfo.getGenericParams());
    }



    /**
     * 解析classInfoList
     * @param baseClass
     * @param otherClass
     * @param classInfoList
     */
    protected void parseClassInfoList(Set<String> baseClass, Set<String> otherClass, List<ClassInfo> classInfoList) {
        if (!CollectionUtils.isEmpty(classInfoList)) {
            for (ClassInfo classInfo : classInfoList) {
                parseClassInfo(baseClass, otherClass, classInfo);
            }
        }
    }

    /**
     * 解析generic 泛型中的关键词
     * @param baseClass
     * @param otherClass
     * @param genericList
     */
    protected void parseGenericList(Set<String> baseClass, Set<String> otherClass, List<Generic> genericList) {
        if (!CollectionUtils.isEmpty(genericList)) {
            for (Generic generic : genericList) {
                if (StringTools.isEmpty(generic.getName())) {
                    throw new RuntimeException("模板错误,缺失数据");
                }

                if (StringTools.isEmpty(generic.getType())) {
                    continue;
                }

                if (StringTools.isEmpty(generic.getPackageName())) {
                    otherClass.add(generic.getType());
                } else {
                    baseClass.add("import " + basePackage + "." + generic.getPackageName() + "." + generic.getType() + ";\n");
                }
            }
        }
    }

    /**
     * 提取genericParam 泛型参数中的关键词
     * @param baseClass
     * @param otherClass
     * @param genericParamList
     */
    protected void parseGenericParamList(Set<String> baseClass, Set<String> otherClass, List<GenericParam> genericParamList) {
        if (!CollectionUtils.isEmpty(genericParamList)) {

            for (GenericParam genericParam : genericParamList) {
                if (StringTools.isEmpty(genericParam.getName())) {
                    throw new RuntimeException("模板错误,缺失数据");
                }

                if (StringTools.isEmpty(genericParam.getPackageName())) {
                    otherClass.add(genericParam.getName());
                } else {
                    baseClass.add("import " + basePackage + "." + genericParam.getPackageName() + "." + genericParam.getName() + ";\n");
                }
            }
        }
    }


    /**
     * 分析包含的关键词,返回 import + 全类名;
     * @param keyList
     */
    protected Set<String> analyseClass(Set<String> keyList) {
        Set<String> importList = new HashSet<>();

        for (String key : keyList) {

            String className = ClassTypes.getByKeyword(key);
            if (!StringTools.isEmpty(className)) {
                importList.add("import " + className + ";\n");
            } else {
                log.warn(key + " is not found!");
            }
        }

        // TODO 结合消息中间件,用python在jar包中找类
        return importList;
    }


    /**
     * 写入构造方法
     * @param builder
     * @param className
     * @param variableList
     */
    protected void writeConstructor(StringBuilder builder, String className, List<Variable> variableList) {
        // 写入无参构造方法
        builder.append("\tpublic ").append(className).append("() {\n");
        builder.append("\t}\n");

        if (CollectionUtils.isEmpty(variableList)) {
            return;
        }

        builder.append("\n");


        // 写入有参构造方法
        builder.append("\tpublic ").append(className).append("(");
        for (Variable variable : variableList) {
            if (StringTools.isEmpty(variable.getName()) || StringTools.isEmpty(variable.getType().getClassName())) {
                throw new RuntimeException("模板错误,缺失数据");
            }

            builder.append(variable.getType().getClassName())
                    .append(" ")
                    .append(variable.getName());
            if (variableList.indexOf(variable)!= variableList.size() - 1) {
                builder.append(", ");
            }
        }
        builder.append(") {\n");

        for (Variable variable : variableList) {
            if (StringTools.isEmpty(variable.getName()) || StringTools.isEmpty(variable.getType().getClassName())) {
                throw new RuntimeException("模板错误,缺失数据");
            }

            builder.append("\t\tthis.")
                    .append(variable.getName())
                    .append(" = ")
                    .append(variable.getName())
                    .append(";\n");
        }

        builder.append("\t}\n");
    }

    /**
     * 写入getter和setter方法
     * @param builder
     * @param variableList
     */
    protected void writeGetterAndSetter(StringBuilder builder, List<Variable> variableList) {
        if (CollectionUtils.isEmpty(variableList)) {
            return;
        }

        for (Variable variable : variableList) {
            if (StringTools.isEmpty(variable.getName()) || StringTools.isEmpty(variable.getType().getClassName())) {
                throw new RuntimeException("模板错误,缺失数据");
            }

            builder.append("\n");

            // 写入setter
            builder.append("\tpublic void set")
                    .append(StringTools.firstToUppercase(variable.getName()))
                    .append("(")
                    .append(variable.getType().getClassName())
                    .append(" ")
                    .append(variable.getName())
                    .append(") {\n");
            builder.append("\t\tthis.")
                    .append(variable.getName())
                    .append(" = ")
                    .append(variable.getName())
                    .append(";\n");
            builder.append("\t}\n");

            builder.append("\n");

            // 写入getter
            builder.append("\tpublic ")
                    .append(variable.getType().getClassName())
                    .append(" get")
                    .append(StringTools.firstToUppercase(variable.getName()))
                    .append("() {\n");
            builder.append("\t\treturn this.")
                    .append(variable.getName())
                    .append(";\n");
            builder.append("\t}\n");
        }
    }

}
