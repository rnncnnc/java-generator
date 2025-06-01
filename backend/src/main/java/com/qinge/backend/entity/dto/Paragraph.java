package com.qinge.backend.entity.dto;

/**
 * @Data: 2025/6/1 9:26
 * @Author: Lc
 * @Description:
 */


public class Paragraph {

    private Boolean isFor;

    private String content;

    public Paragraph() {
    }

    public Paragraph(Boolean isFor, String content) {
        this.isFor = isFor;
        this.content = content;
    }

    public Boolean getFor() {
        return isFor;
    }

    public void setFor(Boolean aFor) {
        isFor = aFor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
