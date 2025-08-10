package com.qinge.backend.service;

import com.qinge.backend.entity.common.FileObj;

import java.util.List;

/**
 * @Data: 2025/8/9 16:06
 * @Author: Lc
 * @Description:
 */


public interface TemplateTypeService {

    // 获取模板类型 及 子目录(模板) 列表
    List<FileObj> getTemplateList();

    // 添加模板类型
    void addTemplateType(String type);

    // 删除模板类型
    void deleteTemplateType(String type);

    // 更新模板类型
    void updateTemplateType(String type, String newType);
}
