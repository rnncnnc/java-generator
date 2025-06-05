package com.qinge.backend.entity.dto.template.object.java;

/**
 * @Data: 2025/6/5 19:27
 * @Author: Lc
 * @Description:
 */


public class ClassInfo {

    // 类名
    private String className;

    // 包名
    private String packageName;

    // 是否是泛型
    private Boolean isGeneric;

    public ClassInfo() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Boolean getGeneric() {
        return isGeneric;
    }

    public void setGeneric(Boolean generic) {
        isGeneric = generic;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "className='" + className + '\'' +
                ", packageName='" + packageName + '\'' +
                ", isGeneric=" + isGeneric +
                '}';
    }
}
