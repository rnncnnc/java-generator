package com.qinge.backend.builder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Data: 2025/5/31 23:31
 * @Author: Lc
 * @Description:
 */


public class PageBuilder extends Builder {
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

            if (text.equals("Integer")) {
                importList.add(Integer.class.getName());
            }
        }

        return importList;
    }
}
