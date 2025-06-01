package com.qinge.backend.entity.dto;

import java.util.List;

/**
 * @Data: 2025/5/30 12:55
 * @Author: Lc
 * @Description:
 */


public class Index {

    // 索引名
    private String Name;

    // 索引关联字段
    private String Fields;

    public Index() {
    }

    public Index(String name, String fields) {
        Name = name;
        Fields = fields;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFields() {
        return Fields;
    }

    public void setFields(String fiields) {
        Fields = fiields;
    }

    @Override
    public String toString() {
        return "Index{" +
                "Name='" + Name + '\'' +
                ", Fiields=" + Fields +
                '}';
    }
}
