package com.qinge.backend.entity.dto.template.java;

import java.util.List;

/**
 * @Data: 2025/6/1 13:42
 * @Author: Lc
 * @Description:
 */


public class MethodParameter {

    // 参数名
    private String name;

    // 参数类型
    private String type;

    // 注解
    private List<Annotation> annotations;

    public MethodParameter() {
    }

    public MethodParameter(String name, String type, List<Annotation> annotations) {
        this.name = name;
        this.type = type;
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

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }
}
