package com.qinge.backend.entity.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Data: 2025/5/30 15:46
 * @Author: Lc
 * @Description:
 */


public class Template {

    /**
     * 整体框架
     */
    private List<String> frame;

    /**
     * 变量
     */
    private List<String> variable;

    /**
     * 通用方法
     */
    private List<String> methods;

    /**
     * 依赖的注解
     */
    private Set<String> annotation;

    /**
     * 关键词
     */
    private Set<String> keyword;

    public Template() {
    }

    public Set<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(Set<String> keyword) {
        this.keyword = keyword;
    }

    public Set<String> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Set<String> annotation) {
        this.annotation = annotation;
    }

    public List<String> getFrame() {
        List<String> temp = new ArrayList<>(frame);

        temp.remove(temp.size() - 1);
        return temp;
    }

    public void setFrame(List<String> frame) {
        this.frame = frame;
    }

    public List<String> getVariable() {
        return variable;
    }

    public void setVariable(List<String> variable) {
        this.variable = variable;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    @Override
    public String toString() {
        return "Template{" +
                "frame=" + frame +
                ", variable=" + variable +
                ", methods=" + methods +
                '}';
    }
}
