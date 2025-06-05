package com.qinge.backend.builder;

import com.qinge.backend.entity.dto.template.object.FileObject;
import com.qinge.backend.entity.dto.template.object.java.*;
import com.qinge.backend.entity.enums.ClassType;
import com.qinge.backend.utils.ClassTools;
import com.qinge.backend.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.tools.Tool;
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

    /**
     * 构建文件
     * @param fileObj
     * @throws IOException
     */
    @Override
    public void build(FileObject fileObj) throws IOException {
        JavaClass javaClass = (JavaClass) fileObj;

        String filePath = temPath + File.separator + javaClass.getPackageName() + File.separator + javaClass.getType() + ".java";

        File parentDir = new File(filePath).getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) { // 递归创建多级目录
            throw new IOException("父目录创建失败: " + parentDir.getAbsolutePath());
        }

        // 写入内容
        String content = writeContent(javaClass);

        // 3. 写入文件（自动创建文件，若已存在则覆盖）
        Files.write(Paths.get(filePath), content.getBytes());
        log.info("写入文件成功: " + javaClass.getName());

    }

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
        writeAnnotation(builder, javaClass.getAnnotations(), "");

        if (StringTools.isEmpty(javaClass.getName()) || StringTools.isEmpty(javaClass.getType())) {
            throw new RuntimeException("模板错误,缺失数据");
        }
        // 写入类开头
        builder.append(javaClass.getPermission()).append(" ").append(javaClass.getName()).append(" ").append(javaClass.getType());

        writeGeneric(builder, javaClass.getGenerics());

        // 写入父类 extend implements
        if (!CollectionUtils.isEmpty(javaClass.getParents())) {

            for (Parent parent : javaClass.getParents()) {
                if (StringTools.isEmpty(parent.getName())) {
                    continue;
                }

                builder.append(" ").append(parent.getName()).append(" ").append(parent.getType().getClassName());
                if (!CollectionUtils.isEmpty(parent.getGenerics())) {

                    Boolean tag = false;

                    builder.append("<");

                    for (ClassInfo generic : parent.getGenerics()) {
                        if (StringTools.isEmpty(generic.getClassName())) {
                            continue;
                        }

                        builder.append(generic.getClassName());

                        if (parent.getGenerics().indexOf(generic) != parent.getGenerics().size() - 1) {
                            builder.append(", ");
                        }

                        tag = true;
                    }

                    if (tag) {
                        builder.append(">");
                    } else {
                        builder.setLength(builder.length() - 1);
                    }
                }
            }
        }

        builder.append(" {\n");

        // 写入变量
        writeVariable(builder, javaClass.getVariables());

        // 写入方法
        writeMethod(builder, javaClass.getMethods());

        builder.append("\n");
        builder.append("\n");

        // 写入构造方法
        if (javaClass.getConstruct()) {
            writeConstructor(builder, javaClass.getName(), javaClass.getVariables());
        }

        // 写入getter和setter方法
        if (javaClass.getGetAndSet()) {
            writeGetterAndSetter(builder, javaClass.getVariables());
        }

        // 类结尾
        builder.append("}");

        return builder.toString();
    }

    protected void writeGeneric(StringBuilder builder, List<Generic> genericList) {
        // 写入泛型
        if (!CollectionUtils.isEmpty(genericList)) {
            builder.append("<");

            Boolean tag = false;

            for (Generic generic : genericList) {
                if (StringTools.isEmpty(generic.getName())) {
                    continue;
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

                tag = true;
            }

            if (tag) {
                builder.append(">");
            } else {
                builder.setLength(builder.length() - 1);
            }

        }
    }

    /**
     * 写入注解
     * @param builder
     * @param annotationList
     */
    protected void writeAnnotation(StringBuilder builder, List<Annotation> annotationList, String prefix) {
        if (CollectionUtils.isEmpty(annotationList)) {
            return;
        }

        // 写入注解
        for (Annotation annotation : annotationList) {
            if (StringTools.isEmpty(annotation.getName())) {
                continue;
            }
            builder.append(prefix).append("@").append(annotation.getName());

            // 如果注解有参数，写入参数
            if (!CollectionUtils.isEmpty(annotation.getParams())) {
                builder.append("(");
                for (AnnotationParam annotationParam : annotation.getParams()) {

                    builder.append(annotationParam.getKey()).append(" = ");

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
                builder.append(")\n");
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
                continue;
            }

            // 写入注释
            writeComment(builder, variable.getComment(), "\t");

            // 写入注解
            writeAnnotation(builder, variable.getAnnotations(), "\t");

            // 写入变量
            builder.append("\t")
                    .append(variable.getPermission())
                    .append(" ")
                    .append(variable.getType().getClassName())
                    .append(" ")
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
                continue;
            }

            // 写入注释
            writeComment(builder, method.getComment(), "\t");

            // 写入注解
            writeAnnotation(builder, method.getAnnotations(), "\t");

            // 写入方法
            builder.append("\t").append(method.getPermission()).append(" ");

            // 写入泛型
            if (!CollectionUtils.isEmpty(method.getGenerics())) {
                builder.append("<");

                Boolean tag = false;

                for (Generic generic : method.getGenerics()) {
                    if (StringTools.isEmpty(generic.getName())) {
                        continue;
                    }

                    builder.append(generic.getName());

                    if (!StringTools.isEmpty(generic.getType())) {
                        builder.append(" extends ")
                                .append(generic.getType());
                    }

                    if (method.getGenerics().indexOf(generic) != method.getGenerics().size() - 1) {
                        builder.append(", ");
                    }

                    tag = true;
                }

                if (tag) {
                    builder.append(">");
                } else {
                    builder.setLength(builder.length() - 1);
                }
            }

            builder.append(method.getReturnType().getClassName()).append(" ").append(method.getName()).append("(");

            // 写入参数
            if (!CollectionUtils.isEmpty(method.getParameters())) {

                for (MethodParameter methodParameter : method.getParameters()) {

                    if (StringTools.isEmpty(methodParameter.getName()) || StringTools.isEmpty(methodParameter.getType().getClassName())) {
                        continue;
                    }

                    if (!CollectionUtils.isEmpty(methodParameter.getAnnotations())) {
                        // 写入注解
                        writeAnnotation(builder, methodParameter.getAnnotations(), "\n\t\t\t");
                        builder.append("\t\t\t")
                                .append(methodParameter.getType().getClassName())
                                .append(" ")
                                .append(methodParameter.getName());
                    } else {
                        builder.append(methodParameter.getType()).append(" ").append(methodParameter.getName());
                    }

                    if (method.getParameters().indexOf(methodParameter) != method.getParameters().size() - 1) {
                        builder.append(", ");
                    }
                }
            }
            builder.append(") {\n");

            // 写入方法体
            if (!CollectionUtils.isEmpty(method.getBodyInfo().getBody())) {
                for (String body : method.getBodyInfo().getBody()) {
                    builder.append("\t\t").append(body).append("\n");
                }
            }
            builder.append("\t}\n");
        }
    }

    /**
     * 写入 需要导入的变量
     * @param builder
     * @param javaClass
     */
    protected void writeImport(StringBuilder builder, JavaClass javaClass) {

        System.out.println(javaClass);
        // 设置包名
        String packageName = basePackage + "." + javaClass.getPackageName();
        builder.append("package " + packageName + ";\n");

        // 存储创建的类
        Set<String> baseClass = new HashSet<>();

        // 存储其他jar包的类
        Set<String> otherClass = new HashSet<>();

        // 获取parent中需要导入的类
        if (!CollectionUtils.isEmpty(javaClass.getParents())) {
            for (Parent parent : javaClass.getParents()) {
                if (StringTools.isEmpty(parent.getType().getClassName())) {
                    continue;
                }
                if (StringTools.isEmpty(parent.getType().getPackageName())) {
                    // 否则,需要从环境中导入
                    otherClass.add(parent.getName());
                } else {
                    // 如果有包名 则是创建的类
                    baseClass.add("import " + basePackage + "." + parent.getType().getPackageName() + "." + parent.getType().getClassName() + ";\n");
                }

                // 获取泛型中需要导入的类
                if (!CollectionUtils.isEmpty(parent.getGenerics())) {
                    for (ClassInfo classInfo : parent.getGenerics()) {
                        if (StringTools.isEmpty(classInfo.getClassName())) {
                            continue;
                        }
                        if (StringTools.isEmpty(classInfo.getPackageName())) {
                            otherClass.add(classInfo.getClassName());
                        } else {
                            baseClass.add("import " + basePackage + "." + classInfo.getPackageName() + "." + classInfo.getClassName() + ";\n");
                        }
                    }
                }
            }
        }

        // 获取泛型中需要导入的类
        if (!CollectionUtils.isEmpty(javaClass.getGenerics())) {
            for (Generic generic : javaClass.getGenerics()) {
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

        // 获取注解中需要导入的类
        if (!CollectionUtils.isEmpty(javaClass.getAnnotations())) {
            for (Annotation annotation : javaClass.getAnnotations()) {
                otherClass.add(annotation.getName());
            }
        }

        // 获取变量中需要导入的类
        if (!CollectionUtils.isEmpty(javaClass.getVariables())) {
            for (Variable variable : javaClass.getVariables()) {
                if (StringTools.isEmpty(variable.getType().getClassName()) || variable.getType().getGeneric()) {
                    continue;
                }
                if (StringTools.isEmpty(variable.getType().getPackageName())) {
                    otherClass.add(variable.getType().getClassName());
                } else {
                    baseClass.add("import " + basePackage + "." + variable.getType().getPackageName() + "." + variable.getType().getClassName() + ";\n");
                }

                // 获取变量上注解中需要导入的类
                if (!CollectionUtils.isEmpty(variable.getAnnotations())) {
                    for (Annotation annotation : variable.getAnnotations()) {
                        otherClass.add(annotation.getName());
                    }
                }
            }
        }

        // 获取方法中需要导入的类
        if (!CollectionUtils.isEmpty(javaClass.getMethods())) {
            for (Method method : javaClass.getMethods()) {
                if (StringTools.isEmpty(method.getReturnType().getClassName()) || method.getReturnType().getGeneric()) {
                    continue;
                }
                if (StringTools.isEmpty(method.getReturnType().getPackageName())) {
                    otherClass.add(method.getReturnType().getClassName());
                } else {
                    baseClass.add("import " + basePackage + "." + method.getReturnType().getPackageName() + "." + method.getReturnType().getClassName() + ";\n");
                }

                // 获取变量上注解中需要导入的类
                if (!CollectionUtils.isEmpty(method.getAnnotations())) {
                    for (Annotation annotation : method.getAnnotations()) {
                        otherClass.add(annotation.getName());
                    }
                }

                // 获取泛型中需要导入的类
                if (!CollectionUtils.isEmpty(method.getGenerics())) {
                    for (Generic generic : method.getGenerics()) {
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

                // 获取参数中需要导入的类
                if (!CollectionUtils.isEmpty(method.getParameters())) {
                    for (MethodParameter parameter : method.getParameters()) {
                        if (StringTools.isEmpty(parameter.getType().getClassName())) {
                            continue;
                        }
                        if (StringTools.isEmpty(parameter.getType().getPackageName())) {
                            otherClass.add(parameter.getType().getClassName());
                        } else {
                            baseClass.add("import " + basePackage + "." + parameter.getType().getPackageName() + "." + parameter.getType().getClassName() + ";\n");
                        }

                        // 获取参数注解需要导入的类
                        if (!CollectionUtils.isEmpty(parameter.getAnnotations())) {
                            for (Annotation annotation : parameter.getAnnotations()) {
                                otherClass.add(annotation.getName());
                            }
                        }

                        // 获取方法体内需要导入的类
                        if (!CollectionUtils.isEmpty(method.getBodyInfo().getRequire())) {
                            for (ClassInfo classInfo : method.getBodyInfo().getRequire()) {
                                if (StringTools.isEmpty(classInfo.getClassName())) {
                                    continue;
                                }
                                if (StringTools.isEmpty(classInfo.getPackageName())) {
                                    otherClass.add(classInfo.getClassName());
                                } else {
                                    baseClass.add("import " + basePackage + "." + classInfo.getPackageName() + "." + classInfo.getClassName() + ";\n");
                                }
                            }
                        }
                    }
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
     * 分析包含的关键词,返回 import + 全类名;
     * @param keyList
     */
    protected Set<String> analyseClass(Set<String> keyList) {
        Set<String> importList = new HashSet<>();

        for (String key : keyList) {
            key = StringTools.firstToUppercase(key.toLowerCase());

            String className = ClassType.getByKeyword(key);
            if (className != null) {
                importList.add("import " + className + ";\n");
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
                continue;
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
                continue;
            }

            builder.append("\t\tthis.")
                    .append(variable.getName())
                    .append(" = ")
                    .append(variable.getName())
                    .append("\n");
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
                continue;
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
                    .append("\n");
            builder.append("\t}\n");
        }
    }

}
