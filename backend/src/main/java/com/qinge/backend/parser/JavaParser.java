package com.qinge.backend.parser;

import com.qinge.backend.entity.dto.template.object.FileObject;
import com.qinge.backend.entity.dto.template.object.java.JavaClass;
import com.qinge.backend.entity.dto.template.object.java.*;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Data: 2025/6/1 11:19
 * @Author: Lc
 * @Description:
 */


public class JavaParser extends ObjectParser {


    /**
     * 将 Map 转为 JavaClass（核心解析逻辑）
     * @param templateMap classObj 节点的 Map 数据
     * @return JavaClass 对象
     */
    @Override
    public FileObject parseTemplate(Map<String, Object> templateMap) {
        JavaClass javaClass = new JavaClass();

        // 1. 基础字段映射
        javaClass.setPackageName((String) templateMap.get("packageName"));
        javaClass.setName((String) templateMap.get("name"));
        javaClass.setType((String) templateMap.get("type"));
        javaClass.setPermission((String) templateMap.get("permission"));
        javaClass.setGetAndSet((Boolean) templateMap.get("getAndSet"));
        javaClass.setConstruct((Boolean) templateMap.get("construct"));

        // 2. 泛型列表
        javaClass.setGenerics(parseGenerics((List<Map<String, Object>>) templateMap.get("generics")));

        // 3. 类注释
        javaClass.setComment((List<String>) templateMap.get("comment"));

        javaClass.setExceptions(parseExceptions((List<Map<String, Object>>) templateMap.get("exceptions")));

        javaClass.setExtendsClass(parseClassInfo((Map<String, Object>) templateMap.get("extendsClass")));

        // 4. 父类列表（List<Parent>）
        List<ClassInfo> classInfos = parseClassInfoList((List<Map<String, Object>>) templateMap.get("implementList"));
        javaClass.setImplementsList(classInfos);

        // 5. 类注解列表（List<Annotation>）
        List<Annotation> annotations = parseAnnotations((List<Map<String, Object>>) templateMap.get("annotations"));
        javaClass.setAnnotations(annotations);

        // 6. 变量列表（List<Variable>）
        List<Variable> variables = parseVariables((List<Map<String, Object>>) templateMap.get("variables"));
        javaClass.setVariables(variables);

        // 7. 方法列表（List<Method>）
        List<Method> methods = parseMethods((List<Map<String, Object>>) templateMap.get("methods"));
        javaClass.setMethods(methods);

        return javaClass;
    }

    /**
     * 解析classInfo
     * @param classInfoMap
     * @return
     */
    private ClassInfo parseClassInfo(Map<String, Object> classInfoMap) {
        ClassInfo classInfo = new ClassInfo();

        if (CollectionUtils.isEmpty(classInfoMap)) return classInfo;

        classInfo.setClassName((String) classInfoMap.get("className"));
        classInfo.setPackageName((String) classInfoMap.get("packageName"));
        classInfo.setGeneric((Boolean) classInfoMap.get("isGeneric"));
        classInfo.setGenericParams(parseGenericParam((List<Map<String, Object>>) classInfoMap.get("genericParams")));

        return classInfo;
    }

    /**
     * 解析泛型参数
     * @param genericParamMaps
     * @return
     */
    private List<GenericParam> parseGenericParam(List<Map<String, Object>> genericParamMaps) {
        List<GenericParam> genericParams = new ArrayList<>();
        if (CollectionUtils.isEmpty(genericParamMaps)) {
            return genericParams;
        }
        for (Map<String, Object> genericParamMap : genericParamMaps) {
            GenericParam genericParam = new GenericParam();
            genericParam.setName((String) genericParamMap.get("name"));
            genericParam.setPackageName((String) genericParamMap.get("packageName"));

            genericParams.add(genericParam);
        }
        return genericParams;
    }

    /**
     * 解析classInfo列表
     * @param classInfoMaps
     * @return
     */
    private List<ClassInfo> parseClassInfoList(List<Map<String, Object>> classInfoMaps) {
        List<ClassInfo> classInfoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(classInfoMaps)) {
            return classInfoList;
        }
        for (Map<String, Object> classInfoMap : classInfoMaps) {
            ClassInfo classInfo = parseClassInfo(classInfoMap);
            classInfoList.add(classInfo);
        }
        return classInfoList;
    }

    /**
     * 解析异常列表
     * @param exceptionMaps
     * @return
     */
    private List<ClassInfo> parseExceptions(List<Map<String, Object>> exceptionMaps) {
        List<ClassInfo> exceptions = new ArrayList<>();
        if (CollectionUtils.isEmpty(exceptionMaps)) return exceptions;
        List<ClassInfo> classInfos = parseClassInfoList(exceptionMaps);
        return classInfos;
    }


    // 解析注解列表
    private List<Annotation> parseAnnotations(List<Map<String, Object>> annotationMaps) {
        List<Annotation> annotations = new ArrayList<>();
        if (CollectionUtils.isEmpty(annotationMaps)) return annotations;

        for (Map<String, Object> annMap : annotationMaps) {
            Annotation annotation = new Annotation();
            annotation.setName((String) annMap.get("name"));
            // 注解参数（List<AnnotationParam>）
            List<Map<String, Object>> paramMaps = (List<Map<String, Object>>) annMap.get("params");
            List<AnnotationParam> params = new ArrayList<>();
            if (!CollectionUtils.isEmpty(paramMaps)) {
                for (Map<String, Object> paramMap : paramMaps) {
                    AnnotationParam param = new AnnotationParam();
                    param.setKey((String) paramMap.get("key"));
                    param.setValue((String) paramMap.get("value"));
                    params.add(param);
                }
            }
            annotation.setParams(params);
            annotations.add(annotation);
        }
        return annotations;
    }

    // 新增：解析 generics 列表（处理 name 和 type）
    private List<Generic> parseGenerics(List<Map<String, Object>> genericMaps) {
        List<Generic> generics = new ArrayList<>();
        if (CollectionUtils.isEmpty(genericMaps)) return generics;
        for (Map<String, Object> genericMap : genericMaps) {
            Generic generic = new Generic();
            generic.setName((String) genericMap.get("name"));
            generic.setType((String) genericMap.get("type"));
            generic.setPackageName((String) genericMap.get("packageName"));
            generics.add(generic);
        }
        return generics;
    }

    // 解析变量列表
    private List<Variable> parseVariables(List<Map<String, Object>> variableMaps) {
        List<Variable> variables = new ArrayList<>();
        if (CollectionUtils.isEmpty(variableMaps)) return variables;

        for (Map<String, Object> varMap : variableMaps) {
            Variable variable = new Variable();
            variable.setName((String) varMap.get("name"));
            variable.setType(parseClassInfo((Map<String, Object>) varMap.get("type")));
            variable.setPermission((String) varMap.get("permission"));
            variable.setComment((List<String>) varMap.get("comment"));
            variable.setGeneric((Boolean) varMap.get(""));
            // 变量注解
            variable.setAnnotations(parseAnnotations((List<Map<String, Object>>) varMap.get("annotations")));
            variables.add(variable);
        }
        return variables;
    }

    // 解析方法列表
    private List<Method> parseMethods(List<Map<String, Object>> methodMaps) {
        List<Method> methods = new ArrayList<>();
        if (CollectionUtils.isEmpty(methodMaps)) return methods;

        for (Map<String, Object> methodMap : methodMaps) {
            Method method = new Method();
            method.setName((String) methodMap.get("name"));
            method.setReturnType(parseClassInfo((Map<String, Object>) methodMap.get("returnType"))); // 注意 YAML 中是 "returnValue"
            method.setPermission((String) methodMap.get("permission"));
            method.setComment((List<String>) methodMap.get("comment"));
            method.setGenerics(parseGenerics((List<Map<String, Object>>) methodMap.get("generics")));
            method.setExceptions(parseExceptions((List<Map<String, Object>>) methodMap.get("exceptions")));
            // 方法注解
            method.setAnnotations(parseAnnotations((List<Map<String, Object>>) methodMap.get("annotations")));
            // 方法参数（List<MethodParameter>）
            List<Map<String, Object>> paramMaps = (List<Map<String, Object>>) methodMap.get("parameters");
            List<MethodParameter> parameters = new ArrayList<>();
            if (!CollectionUtils.isEmpty(paramMaps)) {
                for (Map<String, Object> paramMap : paramMaps) {
                    MethodParameter param = new MethodParameter();
                    param.setName((String) paramMap.get("name"));
                    param.setType(parseClassInfo((Map<String, Object>) paramMap.get("type")));
                    param.setAnnotations(parseAnnotations((List<Map<String, Object>>) paramMap.get("annotations")));
                    parameters.add(param);
                }
            }
            method.setParameters(parameters);
            // 方法体
            Map<String, Object> bodyInfoMap = (Map<String, Object>) methodMap.get("bodyInfo");
            if (bodyInfoMap != null) {
                MethodBody bodyInfo = new MethodBody();
                bodyInfo.setRequire(parseClassInfoList((List<Map<String, Object>>) bodyInfoMap.get("require")));
                bodyInfo.setBody((List<String>) bodyInfoMap.get("body"));

                method.setBodyInfo(bodyInfo);
            }

            methods.add(method);
        }
        return methods;
    }

}
