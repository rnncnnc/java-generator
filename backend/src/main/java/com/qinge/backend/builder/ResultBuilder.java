package com.qinge.backend.builder;

import java.util.HashSet;
import java.util.Set;

/**
 * @Data: 2025/5/31 23:56
 * @Author: Lc
 * @Description:
 */


public class ResultBuilder extends Builder {
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

            if (text.equals("String")) {
                importList.add(String.class.getName());
            } else if (text.equals("Integer")) {
                importList.add(Integer.class.getName());
            } else if (text.equals("Object")) {
                importList.add(Object.class.getName());
            }
        }

        return importList;
    }
}
