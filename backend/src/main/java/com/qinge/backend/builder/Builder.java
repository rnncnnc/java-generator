package com.qinge.backend.builder;

import com.qinge.backend.entity.dto.template.FileObj;
import com.qinge.backend.entity.dto.template.Template;

/**
 * @Data: 2025/6/1 14:16
 * @Author: Lc
 * @Description:
 */


public interface Builder<T> {
    // 基础包名
    String basePackage = null;

    // 临时目录
    String temPath = null;

    /**
     * 构建文件
     * @param template
     */
    void build(T template);
}
