package com.qinge.backend.entity.dto.template.java;

import java.util.List;

/**
 * @Data: 2025/6/1 13:16
 * @Author: Lc
 * @Description:
 */


public class Method {

    // 方法名
    private String name;

    // 返回参数
    private String returnValue;

    // 权限
    private String permission;

    // 注释
    private List<String> comment;

    // 注解
    private List<Annotation> annotations;

    // 参数
    private List<MethodParameter> parameters;

    // 方法体
    private List<String> body;

    public Method() {
    }

    public Method(String name, String returnValue, String permission, List<String> comment, List<Annotation> annotations, List<MethodParameter> parameters, List<String> body) {
        this.name = name;
        this.returnValue = returnValue;
        this.permission = permission;
        this.comment = comment;
        this.annotations = annotations;
        this.parameters = parameters;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
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

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }
}
