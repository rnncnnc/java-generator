package com.qinge.backend.entity.template.object.java;

import java.io.Serializable;
import java.util.List;

/**
 * @Data: 2025/6/5 19:26
 * @Author: Lc
 * @Description:
 */


public class MethodBody implements Serializable {

    private List<String> body;

    private List<ClassInfo> require;

    public MethodBody() {
    }

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }

    public List<ClassInfo> getRequire() {
        return require;
    }

    public void setRequire(List<ClassInfo> require) {
        this.require = require;
    }

    @Override
    public String toString() {
        return "MethodBody{" +
                "body=" + body +
                ", require=" + require +
                '}';
    }
}
