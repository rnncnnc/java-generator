package com.qinge.backend.dto;

import com.qinge.backend.entity.table.Table;

import java.util.List;

/**
 * @Data: 2025/8/3 11:47
 * @Author: Lc
 * @Description: 基本构建信息
 */


public class BaseInfo {

    private String basePackage;

    private List<Table> tableList;

    private TemplateDto templateDto;

    private String tempPath;

    public BaseInfo(String basePackage, List<Table> tableList, TemplateDto templateDto, String tempPath) {
        this.basePackage = basePackage;
        this.tableList = tableList;
        this.templateDto = templateDto;
        this.tempPath = tempPath;
    }

    public BaseInfo() {
    }

    public TemplateDto getTemplate() {
        return templateDto;
    }

    public void setTemplate(TemplateDto templateDto) {
        this.templateDto = templateDto;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }

    @Override
    public String toString() {
        return "BaseInfoDTO{" +
                "basePackage='" + basePackage + '\'' +
                ", tableList=" + tableList +
                ", template=" + templateDto +
                ", tempPath='" + tempPath + '\'' +
                '}';
    }
}
