package com.qinge.backend.builder;

import com.qinge.backend.entity.dto.template.object.FileObject;

import java.io.IOException;

/**
 * @Data: 2025/6/1 14:17
 * @Author: Lc
 * @Description:
 */


public abstract class FileBuilder implements Builder {

    // 基础包名
    protected String basePackage;

    // 临时目录
    protected String temPath;

    /**
     * 构建文件
     */
    @Override
    abstract public void build(FileObject fileObj) throws IOException;

    public FileBuilder() {
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
