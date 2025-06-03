package com.qinge.backend.parser;

import com.qinge.backend.entity.dto.template.FileObj;
import com.qinge.backend.entity.dto.template.Template;

import java.util.Map;

/**
 * @Data: 2025/6/1 17:28
 * @Author: Lc
 * @Description:
 */


public interface Parser {

    /**
     * 解析模板
     * @param templateMap
     * @return
     */
    FileObj parseTemplate(Map<String, Object> templateMap);
}
