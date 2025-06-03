package com.qinge.backend.entity.dto.template.java;

import java.util.List;

/**
 * @Data: 2025/6/1 13:16
 * @Author: Lc
 * @Description:
 */


public class Variable {

    // 依据什么字段进行循环
    private String forList;

    // 变量
    private List<VariableContext> contexts;

    public Variable() {
    }

    public Variable(String forList, List<VariableContext> contexts) {
        this.forList = forList;
        this.contexts = contexts;
    }

    public String getForList() {
        return forList;
    }

    public void setForList(String forList) {
        this.forList = forList;
    }

    public List<VariableContext> getContexts() {
        return contexts;
    }

    public void setContexts(List<VariableContext> contexts) {
        this.contexts = contexts;
    }
}
