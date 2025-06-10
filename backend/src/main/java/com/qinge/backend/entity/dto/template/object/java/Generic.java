package com.qinge.backend.entity.dto.template.object.java;

import java.io.Serializable;

/**
 * @Data: 2025/6/5 11:00
 * @Author: Lc
 * @Description:
 */


public class Generic implements Serializable {

    // 泛型名
    private String name;

    // 泛型类型 extends
    private String type;

    // 类型的包名
    private String packageName;

    public Generic() {
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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

    @Override
    public String toString() {
        return "Generic{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}
