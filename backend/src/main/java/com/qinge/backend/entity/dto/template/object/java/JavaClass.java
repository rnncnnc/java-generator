package com.qinge.backend.entity.dto.template.object.java;


import com.qinge.backend.entity.dto.template.object.FileObject;
import com.qinge.backend.entity.dto.template.object.java.*;

import java.util.List;

/**
 * @Data: 2025/6/1 13:12
 * @Author: Lc
 * @Description:
 */


public class JavaClass implements FileObject {

    // 包名
    private String packageName;

    // 父类
    private List<Parent> parents;

    // 类名
    private String name;

    // 类型  class  interface
    private String type;

    // 权限  public  private  protected  default
    private String permission;

    // 泛型
    private List<Generic> generics;

    // 是否需要get set
    private Boolean getAndSet;

    // 是否需要构造方法
    private Boolean construct;

    // 注释
    List<String> comment;

    // 注解
    private List<Annotation> annotations;

    // 变量
    private List<Variable> variables;

    // 方法
    private List<Method> methods;

    public JavaClass() {
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
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

    public List<Generic> getGenerics() {
        return generics;
    }

    public void setGenerics(List<Generic> generics) {
        this.generics = generics;
    }

    public Boolean getGetAndSet() {
        return getAndSet;
    }

    public void setGetAndSet(Boolean getAndSet) {
        this.getAndSet = getAndSet;
    }

    public Boolean getConstruct() {
        return construct;
    }

    public void setConstruct(Boolean construct) {
        this.construct = construct;
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

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    @Override
    public String toString() {
        return "JavaClass{" +
                "packageName='" + packageName + '\'' +
                ", parents=" + parents +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", permission='" + permission + '\'' +
                ", generics=" + generics +
                ", getAndSet=" + getAndSet +
                ", construct=" + construct +
                ", comment=" + comment +
                ", annotations=" + annotations +
                ", variables=" + variables +
                ", methods=" + methods +
                '}';
    }
}
