package com.qinge.backend.builder.java;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.qinge.backend.entity.table.Field;
import com.qinge.backend.entity.template.object.java.JavaClass;
import com.qinge.backend.entity.template.object.java.Method;
import com.qinge.backend.utils.ClassTools;
import com.qinge.backend.utils.StringTools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Data: 2025/6/5 12:52
 * @Author: Lc
 * @Description:
 */


public class ControllerBuilder extends JavaBuilder {

    @Override
    protected JavaClass beforeWrite(JavaClass javaClass) throws JsonProcessingException {
        // 方法列表
        List<Method> methods = javaClass.getMethods();

        List<Method> methodList = new ArrayList<>(methods);

        // 替换关键词后的方法列表
        List<Method> replacedMethods = new ArrayList<>();
        Iterator<Method> iterator = methodList.iterator();
        while (iterator.hasNext())  {
            Method method = iterator.next();

            // 替换关键词
            if (hasKeyword(method)) {
                for (Field field : table.getFields()) {
                    // 如果数据库字段的索引值不为空，说明是索引字段，复制方法体信息
                    if (StringTools.isEmpty(field.getFieldKey()) || field.getFieldKey().equals("Index")) {
                        continue;
                    }

                    Method methodCopy = ClassTools.deepCopy(method);

                    // 替换关键词
                    Method repMethod = replaceKeyword(methodCopy, field);

                    replacedMethods.add(repMethod);
                }

                iterator.remove();
            }
        }


        methodList.addAll(replacedMethods);

        // 深拷贝类
        JavaClass javaClassUsed = ClassTools.deepCopy(javaClass);

        javaClassUsed.setMethods(methodList);

        // 返回替换关键词后的类
        return replaceKeyword(javaClassUsed, table);
    }

    /**
     * 是否包含关键字
     * @param method
     * @return
     */
    private Boolean hasKeyword(Method method) {
        Set<String> keys = StringTools.extractKeys(method.toString());

        return keys.toString().contains("Field");
    }
}
