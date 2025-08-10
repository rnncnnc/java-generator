package com.qinge.backend.service;

import com.qinge.backend.dto.TemplateDto;

import java.util.Map;

/**
 * @Data: 2025/8/9 16:11
 * @Author: Lc
 * @Description:
 */


public interface TemplateService {

    /**
     * 获取模板列表
     * @param type 模板类型
     * @param file 模板文件
     * @return 模板列表
     */
    Map<String, Object> getTemplate(String type, String file);

    /**
     * 添加模板文件
     * @param templateDto 模板文件
     */
    void addTemplateFile(TemplateDto templateDto);

    /**
     * 删除模板文件
     * @param type 模板类型
     * @param file 模板文件名
     */
    void deleteTemplateFile(String type, String file);

    /**
     * 更新模板文件
     * @param type 模板类型
     * @param file 模板文件名
     * @param templateDto 模板文件
     */
    void updateTemplateFile(String type, String file, TemplateDto templateDto);

    /**
     * 重置模板
     */
    void resetTemplate();
}
