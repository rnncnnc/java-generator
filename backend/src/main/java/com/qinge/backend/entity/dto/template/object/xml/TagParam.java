package com.qinge.backend.entity.dto.template.object.xml;

import java.io.Serializable;

/**
 * @Data: 2025/6/7 0:20
 * @Author: Lc
 * @Description:
 */


public class TagParam implements Serializable {

    private String key;

    private String value;

    public TagParam() {
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
        return "TagParam{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
