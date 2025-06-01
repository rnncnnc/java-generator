package com.qinge.backend.builder;

import com.qinge.backend.utils.Tools;

import java.util.HashSet;
import java.util.Set;

/**
 * @Data: 2025/5/31 21:51
 * @Author: Lc
 * @Description:
 */


public class MapperBuilder extends Builder{
    @Override
    protected Set<String> extractAnnotation() {
        return null;
    }

    @Override
    protected Set<String> extractKeyword() {
        Set set = new HashSet<>();

        // 添加pojo的导入
        if (Tools.isTemplateHas(template, "Pojo")) {
            set.add(basePackageName + ".entity.pojo." + table.getTableName() + "Pojo");
        }
        // else if (Tools.isTemplateHas(template, "BaseMapper")) {
        //     set.add(packageName + ".mapper.BaseMapper");
        // }

        return set;
    }
}
