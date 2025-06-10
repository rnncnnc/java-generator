package com.qinge.backend.entity.template.object.xml;

import java.io.Serializable;
import java.util.List;

/**
 * @Data: 2025/6/7 0:20
 * @Author: Lc
 * @Description:
 */


public class Tag implements Serializable {

    // 标签名 如果标签名为NONE 则表示标签内容为文本内容
    private String name;

    // 标签属性
    private List<TagParam> params;

    // 标签内容
    private List<Tag> tags;

    // 注释
    private List<String> comment;

    public Tag() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TagParam> getParams() {
        return params;
    }

    public void setParams(List<TagParam> params) {
        this.params = params;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", params=" + params +
                ", tags=" + tags +
                ", comment='" + comment + '\'' +
                '}';
    }
}
