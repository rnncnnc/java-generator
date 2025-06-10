package com.qinge.backend.parser.file;

import com.qinge.backend.entity.dto.template.Template;
import com.qinge.backend.entity.dto.template.object.FileObject;

import java.io.IOException;
import java.util.Map;

/**
 * @Data: 2025/6/1 17:28
 * @Author: Lc
 * @Description:
 */


public interface FileParser {

    /**
     * 解析模板
     * @param ymlFilePath
     * @return
     */
    Template parseFromYml(String ymlFilePath) throws IOException;

    /**
     * 将map转为template（完善映射逻辑）
     * @param templateMap 包含模板数据的Map
     * @return JavaClass 对象
     */
    FileObject parseTemplate(Map<String, Object> templateMap);
}
