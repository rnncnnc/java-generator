package com.qinge.backend.entity.dto.template.object.java;

import java.util.List;

/**
 * @Data: 2025/6/1 13:15
 * @Author: Lc
 * @Description:
 */


public class Annotation {

    private String name;

    private List<AnnotationParam> params;

    public Annotation() {
    }

    public Annotation(String name, List<AnnotationParam> params) {
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AnnotationParam> getParams() {
        return params;
    }

    public void setParams(List<AnnotationParam> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Annotation{" +
                "name='" + name + '\'' +
                ", params=" + params +
                '}';
    }
}
