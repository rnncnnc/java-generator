package com.qinge.backend.entity.dto.template.object.java;

import java.io.Serializable;

/**
 * @Data: 2025/6/1 13:32
 * @Author: Lc
 * @Description:
 */


public class AnnotationParam implements Serializable {

    private String key;

    private String value;

    public AnnotationParam() {
    }

    public AnnotationParam(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AnnotationParam{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
