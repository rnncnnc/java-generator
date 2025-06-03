package com.qinge.backend.entity.dto.template.java;

import java.util.List;

/**
 * @Data: 2025/6/1 13:29
 * @Author: Lc
 * @Description:
 */


public class VariableContext {

    // 变量名
    private String name;

    // 变量类型
    private String type;

    // 权限
    private String permission;

    // 注释
    private List<String> comment;

    // 注解
    private List<Annotation> annotations;

    public VariableContext() {
    }

    public VariableContext(String name, String type, String permission, List<String> comment, List<Annotation> annotations) {
        this.name = name;
        this.type = type;
        this.permission = permission;
        this.comment = comment;
        this.annotations = annotations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
