package com.qinge.backend.builder;

import com.qinge.backend.utils.Tools;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Data: 2025/5/31 22:43
 * @Author: Lc
 * @Description:
 */


public class BaseMapperBuilder extends Builder{
    @Override
    protected Set<String> extractAnnotation() {
        return null;
    }

    @Override
    protected Set<String> extractKeyword() {
        // 合并所有数组并提取关键词
        Set<String> result = template.getKeyword();

        // 获取注解的全类名
        Set<String> importList = new HashSet<>();
        for (String s : result) {
            String text = s.replace("^", "");

            if (text.equals("List")) {
                importList.add(List.class.getName());
            } else if (text.equals("Query")) {
                importList.add(basePackageName + ".entity.query.Query");
            }
        }

        return importList;
    }
}
