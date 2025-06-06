package com.qinge.backend.entity.dto.template.object.java;

import java.io.Serializable;
import java.util.List;

/**
 * @Data: 2025/6/1 13:16
 * @Author: Lc
 * @Description:
 */


public class Method implements Serializable {

    // 方法名
    private String name;

    // 返回参数
    private ClassInfo returnType;

    // 权限
    private String permission;

    // 泛型
    private List<Generic> generics;

    // 异常
    private List<ClassInfo> exceptions;

    // 注释
    private List<String> comment;

    // 注解
    private List<Annotation> annotations;

    // 参数
    private List<MethodParameter> parameters;

    // 方法体
    private MethodBody bodyInfo;

    public Method() {
    }

    public List<ClassInfo> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<ClassInfo> exceptions) {
        this.exceptions = exceptions;
    }

    public List<Generic> getGenerics() {
        return generics;
    }

    public void setGenerics(List<Generic> generics) {
        this.generics = generics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassInfo getReturnType() {
        return returnType;
    }

    public void setReturnType(ClassInfo returnType) {
        this.returnType = returnType;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public List<MethodParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<MethodParameter> parameters) {
        this.parameters = parameters;
    }

    public MethodBody getBodyInfo() {
        return bodyInfo;
    }

    public void setBodyInfo(MethodBody bodyInfo) {
        this.bodyInfo = bodyInfo;
    }

    @Override
    public String toString() {
        return "Method{" +
                "name='" + name + '\'' +
                ", returnType=" + returnType +
                ", permission='" + permission + '\'' +
                ", generics=" + generics +
                ", exceptions=" + exceptions +
                ", comment=" + comment +
                ", annotations=" + annotations +
                ", parameters=" + parameters +
                ", bodyInfo=" + bodyInfo +
                '}';
    }
}
