package com.qinge.backend.builder;

import com.qinge.backend.entity.dto.template.java.JavaClass;

/**
 * @Data: 2025/6/1 14:17
 * @Author: Lc
 * @Description:
 */


public abstract class FileBuilder<T> implements Builder<T>{

    // 基础包名
    protected String basePackage;

    // 临时目录
    protected String temPath;

    @Override
    public void build(T template) {
        System.out.println("file builder");
    }

    public FileBuilder() {
    }

    public FileBuilder(String basePackage, String temPath) {
        this.basePackage = basePackage;
        this.temPath = temPath;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTemPath() {
        return temPath;
    }

    public void setTemPath(String temPath) {
        this.temPath = temPath;
    }
}
