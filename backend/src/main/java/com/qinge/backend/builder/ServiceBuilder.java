package com.qinge.backend.builder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Data: 2025/5/31 21:21
 * @Author: Lc
 * @Description:
 */


public class ServiceBuilder extends Builder {

    /**
     * 提取注解
     * @return
     */
    @Override
    protected Set<String> extractAnnotation() {
        return null;
    }

    /**
     * 将关键词 加入到imortList中
     * @return
     */
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
