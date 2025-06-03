package com.qinge.backend.entity.dto.template.java;

import java.util.List;

/**
 * @Data: 2025/6/1 13:23
 * @Author: Lc
 * @Description:
 */


public class Parent {

    // 父类类型    extend    implements
    private String type;

    // 类名
    private String name;

    // 包名
    private String packageName;

    // 泛型
    private List<String> generics;

    public Parent() {
    }

    public Parent(String type, String name, String packageName, List<String> generics) {
        this.type = type;
        this.name = name;
        this.packageName = packageName;
        this.generics = generics;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getGenerics() {
        return generics;
    }

    public void setGenerics(List<String> generics) {
        this.generics = generics;
    }
}
