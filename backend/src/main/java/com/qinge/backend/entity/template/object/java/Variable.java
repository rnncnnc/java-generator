package com.qinge.backend.entity.template.object.java;

import java.io.Serializable;
import java.util.List;

/**
 * @Data: 2025/6/1 13:29
 * @Author: Lc
 * @Description:
 */


public class Variable implements Serializable {

    // 变量名
    private String name;

    // 变量类型
    private ClassInfo type;

    // 是否是泛型变量
    private Boolean isGeneric;

    // 权限
    private String permission;

    // 注释
    private List<String> comment;

    // 注解
    private List<Annotation> annotations;

    public Variable() {
    }


    public Boolean getGeneric() {
        return isGeneric;
    }

    public void setGeneric(Boolean generic) {
        isGeneric = generic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassInfo getType() {
        return type;
    }

    public void setType(ClassInfo type) {
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

    @Override
    public String toString() {
        return "Variable{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", isGeneric=" + isGeneric +
                ", permission='" + permission + '\'' +
                ", comment=" + comment +
                ", annotations=" + annotations +
                '}';
    }
}
