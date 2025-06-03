package com.qinge.backend.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qinge.backend.entity.dto.template.Template;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @Data: 2025/6/1 14:15
 * @Author: Lc
 * @Description:
 */


public class TemplateParser {

    /**
     * 解析java模板
     * @param templatePath
     * @return
     */
    public static Map<String, Object> parseToTemplate(String templatePath) {

        Map<String, Object> templateMap = null;

        try (InputStream inputStream = DataBaseParser.class.getClassLoader().getResourceAsStream(templatePath)) {

            if (inputStream == null) {
                System.err.println("资源文件未找到：" + templatePath);
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
             templateMap = mapper.readValue(inputStream, new TypeReference<>() {});

        } catch (IOException e) {
            System.err.println("读取资源文件失败：" + e.getMessage());
        }

        return templateMap;
    }
}
