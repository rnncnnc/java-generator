package com.qinge.backend.dto;

/**
 * @Data: 2025/8/9 15:25
 * @Author: Lc
 * @Description:
 */


public class Template {

    private String type;
    private String file;
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Template{" +
                "type='" + type + '\'' +
                ", file='" + file + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
