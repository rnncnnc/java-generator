package com.qinge.backend.entity.template.object.java;


import com.qinge.backend.entity.template.object.FileObject;

import java.io.Serializable;
import java.util.List;

/**
 * @Data: 2025/6/1 13:12
 * @Author: Lc
 * @Description:
 */


public class JavaClass implements FileObject, Serializable {

    // 包名
    private String packageName;

    // 继承的父类
    private ClassInfo extendsClass;

    // 实现的接口
    private List<ClassInfo> implementsList;

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

    // 异常
    private List<ClassInfo> exceptions;

    // 注释
    private List<String> comment;

    // 注解
    private List<Annotation> annotations;

    // 变量
    private List<Variable> variables;

    // 方法
    private List<Method> methods;

    public JavaClass() {
    }

    public List<ClassInfo> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<ClassInfo> exceptions) {
        this.exceptions = exceptions;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ClassInfo getExtendsClass() {
        return extendsClass;
    }

    public void setExtendsClass(ClassInfo extendsClass) {
        this.extendsClass = extendsClass;
    }

    public List<ClassInfo> getImplementsList() {
        return implementsList;
    }

    public void setImplementsList(List<ClassInfo> implementsList) {
        this.implementsList = implementsList;
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
                ", extendsClass=" + extendsClass +
                ", implementsList=" + implementsList +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", permission='" + permission + '\'' +
                ", generics=" + generics +
                ", getAndSet=" + getAndSet +
                ", construct=" + construct +
                ", exceptions=" + exceptions +
                ", comment=" + comment +
                ", annotations=" + annotations +
                ", variables=" + variables +
                ", methods=" + methods +
                '}';
    }
}
