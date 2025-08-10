package com.qinge.backend.entity.common;

import java.util.List;

/**
 * @Data: 2025/8/9 15:16
 * @Author: Lc
 * @Description:
 */


public class FileObj {

    private String name;

    private List<FileObj> children;

    public FileObj() {
    }

    public FileObj(String name, List<FileObj> children) {
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FileObj> getChildren() {
        return children;
    }

    public void setChildren(List<FileObj> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "FileObj{" +
                "name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
