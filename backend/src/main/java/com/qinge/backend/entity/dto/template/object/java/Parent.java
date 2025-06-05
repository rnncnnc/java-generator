package com.qinge.backend.entity.dto.template.object.java;

import java.util.List;

/**
 * @Data: 2025/6/1 13:23
 * @Author: Lc
 * @Description:
 */


public class Parent {

    // 类的信息
    private ClassInfo type;

    // 父类类型    extend    implements
    private String name;

    // 泛型
    private List<ClassInfo> generics;

    public Parent() {
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

    public List<ClassInfo> getGenerics() {
        return generics;
    }

    public void setGenerics(List<ClassInfo> generics) {
        this.generics = generics;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", generics=" + generics +
                '}';
    }
}
