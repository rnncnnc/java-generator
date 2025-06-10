package com.qinge.backend.entity.template;

import com.qinge.backend.entity.template.object.FileObject;

/**
 * @Data: 2025/6/1 11:22
 * @Author: Lc
 * @Description:
 */


public class Template {

    // 生成的文件类型 java xml 等
    private String fileType;

    // 生成的文件类型 controller service 等
    private String classType;

    // 具体的模板对象
    private FileObject templateObj;

    public Template() {
    }



    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public FileObject getTemplateObj() {
        return templateObj;
    }

    public void setTemplateObj(FileObject classObj) {
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
