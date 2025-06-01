package com.qinge.backend.builder;

import com.qinge.backend.utils.Tools;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Data: 2025/6/1 0:11
 * @Author: Lc
 * @Description:
 */


public class ControllerBuilder extends Builder {
    @Override
    protected Set<String> extractAnnotation() {
        // 合并所有数组并提取关键词
        Set<String> result = template.getAnnotation();

        // 获取注解的全类名
        Set<String> importList = new HashSet<>();
        for (String s : result) {
            String text = s.replace("@", "");

            if (text.equals("RestController")) {
                importList.add(RestController.class.getName());
            } else if (text.equals("Resource")) {
                importList.add(Resource.class.getName());
            } else if (text.equals("Tag")) {
                importList.add(Tag.class.getName());
            } else if (text.equals("Operation")) {
                importList.add(Operation.class.getName());
            } else if (text.equals("GetMapping")) {
                importList.add(GetMapping.class.getName());
            } else if (text.equals("PostMapping")) {
                importList.add(PostMapping.class.getName());
            } else if (text.equals("DeleteMapping")) {
                importList.add(DeleteMapping.class.getName());
            } else if (text.equals("PutMapping")) {
                importList.add(PutMapping.class.getName());
            } else if (text.equals("RequestBody")) {
                importList.add(RequestBody.class.getName());
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
            } else if (text.equals("Result")) {
                importList.add(packageName + ".entity.vo.Result");
            }
        }


        if (Tools.isTemplateHas(template, "Pojo")) {
            importList.add(basePackageName + ".entity.pojo." + table.getTableName() + "Pojo");
        }
        if (Tools.isTemplateHas(template, "Service")) {
            importList.add(basePackageName + ".service." + table.getTableName() + "Service");
        }

        return importList;
    }
}
