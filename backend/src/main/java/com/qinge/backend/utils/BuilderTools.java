package com.qinge.backend.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * @Data: 2025/6/1 9:50
 * @Author: Lc
 * @Description:
 */


public class BuilderTools {

    /**
     * 替换模板中的关键词
     * @param data
     * @param template
     * @return
     * @param <T>
     */
    public static <T> String replaceData(T data, List<String> template) {
        // 合并集合
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : template) {
            stringBuilder.append(s + "\n");
            if (s.endsWith(";") || s.endsWith("}")) {
                stringBuilder.append("\n");
            }
        }

        String str = stringBuilder.toString();

        // 提取关键词
        Set<String> keys = ParserTools.extractKeys(str, "\\$\\([a-zA-Z]+\\)");

        for (String key : keys) {
            try {
                // 获取类实例
                Class<?> clazz = data.getClass();

                // 获取方法名
                String methodName = "get" + key.replace("$(", "").replace(")", "");

                // 获取方法
                Method method = clazz.getMethod(methodName);

                // 调用方法
                String field = (String) method.invoke(data);

                // 替换字符串中的关键词
                str =  str.replace(key, field);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return str;
    }

    /**
     * 判断模板中是否包含关键词
     * @param template
     * @param str
     * @return
     */
    // public static Boolean isTemplateHas(Template template, String str) {
    //     List<String> frame = template.getFrame();
    //     List<String> variable = template.getVariable();
    //     List<String> methods = template.getMethods();
    //     List<String> list = FileTools.mergeList(frame, variable, methods);
    //
    //     String context = String.join("\n", list);
    //
    //     return context.contains(str);
    // }
}
