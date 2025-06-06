package com.qinge.backend.entity.dto.template.object.java;

import java.io.Serializable;

/**
 * @Data: 2025/6/6 20:53
 * @Author: Lc
 * @Description:
 */


public class GenericParam implements Serializable {

    private String name;

    private String packageName;

    public GenericParam() {
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

    @Override
    public String toString() {
        return "GenericParam{" +
                "name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}
