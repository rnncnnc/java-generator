package com.qinge.backend.entity.dto.template;

/**
 * @Data: 2025/6/1 11:22
 * @Author: Lc
 * @Description:
 */


public class Template<T> {

    private String fileType;

    private T classObj;

    public Template() {
    }

    public Template(String fileType, T classObj) {
        this.fileType = fileType;
        this.classObj = classObj;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public T getClassObj() {
        return classObj;
    }

    public void setClassObj(T classObj) {
        this.classObj = classObj;
    }
}
