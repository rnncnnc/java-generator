package com.qinge.backend.entity.common;

import java.util.List;

/**
 * @Data: 2025/8/9 15:16
 * @Author: Lc
 * @Description:
 */


public class FileObj {

    private String name;

    private String path;

    private List<FileObj> children;

    public FileObj() {
    }

    public FileObj(String name, String path, List<FileObj> children) {
        this.name = name;
        this.path = path;
        this.children = children;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
                ", path='" + path + '\'' +
                ", children=" + children +
                '}';
    }
}
