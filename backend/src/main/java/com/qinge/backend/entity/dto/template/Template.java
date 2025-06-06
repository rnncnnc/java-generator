package com.qinge.backend.entity.dto.template;

import com.qinge.backend.entity.dto.template.object.FileObject;

/**
 * @Data: 2025/6/1 11:22
 * @Author: Lc
 * @Description:
 */


public class Template<T extends FileObject> {

    // 生成的文件类型 pojo controller service mapper xml 等
    private String fileType;

    // 具体的模板对象
    private T templateObj;

    public Template() {
    }

    public Template(String fileType, T classObj) {
        this.fileType = fileType;
        this.templateObj = classObj;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public T getTemplateObj() {
        return templateObj;
    }

    public void setTemplateObj(T classObj) {
        this.templateObj = classObj;
    }

    @Override
    public String toString() {
        return "Template{" +
                "fileType='" + fileType + '\'' +
                ", classObj=" + templateObj +
                '}';
    }
}
