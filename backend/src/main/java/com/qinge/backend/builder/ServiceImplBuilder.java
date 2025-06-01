package com.qinge.backend.builder;

import com.qinge.backend.utils.Tools;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Resource;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Data: 2025/5/31 22:14
 * @Author: Lc
 * @Description:
 */


public class ServiceImplBuilder extends Builder{
    @Override
    protected Set<String> extractAnnotation() {
        // 合并所有数组并提取关键词
        Set<String> result = template.getAnnotation();

        // 获取注解的全类名
        Set<String> importList = new HashSet<>();
        for (String s : result) {
            String text = s.replace("@", "");

            if (text.equals("Service")) {
                importList.add(Service.class.getName());
            } else if (text.equals("Resource")) {
                importList.add(Resource.class.getName());
            }
        }
        return importList;
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
                importList.add(packageName + ".entity.query.Query");
            }
        }


        if (Tools.isTemplateHas(template, "Pojo")) {
            importList.add(basePackageName + ".entity.pojo." + table.getTableName() + "Pojo");
        }


        return importList;
    }
}
