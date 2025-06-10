package com.qinge.backend.entity.template.object.java;

import java.io.Serializable;
import java.util.List;

/**
 * @Data: 2025/6/1 13:42
 * @Author: Lc
 * @Description:
 */


public class MethodParameter implements Serializable {

    // 参数名
    private String name;

    // 参数类型
    private ClassInfo type;

    // 注解
    private List<Annotation> annotations;

    public MethodParameter() {
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

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    @Override
    public String toString() {
        return "MethodParameter{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", annotations=" + annotations +
                '}';
    }
}
