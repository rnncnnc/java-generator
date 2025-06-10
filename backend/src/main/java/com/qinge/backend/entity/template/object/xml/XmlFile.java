package com.qinge.backend.entity.template.object.xml;

import com.qinge.backend.entity.template.object.FileObject;

import java.io.Serializable;
import java.util.List;

/**
 * @Data: 2025/6/7 0:15
 * @Author: Lc
 * @Description:
 */


public class XmlFile implements FileObject, Serializable {

    // 文件名
    private String name;

    // 文件路径
    private String filePath;

    // 文件开头
    private List<String> header;

    // xml 标签
    private List<Tag> tags;


    public XmlFile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "XmlFile{" +
                "name='" + name + '\'' +
                ", filePath='" + filePath + '\'' +
                ", header=" + header +
                ", tags=" + tags +
                '}';
    }
}
