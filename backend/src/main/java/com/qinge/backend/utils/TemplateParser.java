package com.qinge.backend.utils;

import com.qinge.backend.entity.dto.Template;

import java.util.*;

/**
 * @Data: 2025/5/30 15:43
 * @Author: Lc
 * @Description:
 */


public class TemplateParser {


    /**
     * 解析类模型
     * @param templatePath  "template/pojo.template"
     * @return
     */
    public static Template parseClassTemplate(String templatePath) {
        Template template = new Template();
        Deque<String> list = Tools.readResourceFile(templatePath);

        String context = String.join("\n", list);
        // 提取注解
        Set<String> annotation = Tools.extractKeys(context, "@[a-zA-Z]+");

        // 提取关键词
        Set<String> keyword = Tools.extractKeys(context, "\\^[a-zA-Z]+");

        template.setAnnotation(annotation);
        template.setKeyword(keyword);

        // 提取整体框架
        List<String> frame = new ArrayList<>();
        int i = 0;
        while (!list.peek().startsWith("public")) {
            frame.add(list.pollFirst());
            i++;
        }
        frame.add(list.pollFirst());
        frame.add(list.pollLast());

        template.setFrame(frame);

        // 提取变量
        List<String> variable = new ArrayList<>();
        List<String> methods = new ArrayList<>();
        String temp;
        List<String> tempList = new ArrayList<>();
        while ((temp = list.pollFirst()) != null) {
            if (Objects.equals(list.peekFirst(), "") || Objects.equals(list.peekFirst(), "}")) {
                tempList.add(temp);

                // 提取变量
                if (temp.endsWith(";")){
                    variable.addAll(tempList);
                } else if (temp.endsWith("}")) {
                    // 提取方法
                    methods.addAll(tempList);
                }

                list.pollFirst();
                tempList.clear();
                continue;
            }

            if (Objects.equals(temp, "")) {
                continue;
            }

            tempList.add(temp);
        }

        template.setVariable(variable);
        template.setMethods(methods);

        return template;
    }

    public static Template parseXMLTemplate(String templatePath) {
        return null;
    }

    public static void main(String[] args) {
        Template template = parseClassTemplate("template/controller.template");
        System.out.println(template.getVariable());
    }
}
