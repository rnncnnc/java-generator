package com.qinge.backend.builder;

import com.qinge.backend.entity.dto.Field;
import com.qinge.backend.entity.dto.Table;
import com.qinge.backend.entity.dto.Template;
import com.qinge.backend.utils.Tools;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Data: 2025/5/30 15:12
 * @Author: Lc
 * @Description:
 */


public class PojoBuilder extends Builder {

    /**
     * 构建pojo文件时的额外操作
     */
    @Override
    protected void extra() {
        importList = Tools.mergeSet(importList, table.getImportList());
    }

    /**
     * 获取模板中注解的全类名
     * @return
     */
    @Override
    protected Set<String> extractAnnotation() {
        // 合并所有数组并提取关键词
        Set<String> result = template.getAnnotation();

        // 获取注解的全类名
        Set<String> importList = new HashSet<>();
        for (String s : result) {
            String text = s.replace("@", "");

            if (text.equals("Entity")) {
                importList.add(Entity.class.getName());
            } else if (text.equals("Table")) {
                importList.add(jakarta.persistence.Table.class.getName());
            } else if (text.equals("Schema")) {
                importList.add(Schema.class.getName());
            } else if (text.equals("Column")) {
                importList.add(Column.class.getName());
            } else if (text.equals("id")) {
                importList.add(Id.class.getName());
            }
        }

        return importList;
    }

    /**
     * 提取关键词
     * @return
     */
    @Override
    protected Set<String> extractKeyword() {
        return null;
    }

    /**
     * 写入变量
     * @param bw
     * @param fields
     * @param variable
     */
    @Override
    protected void writeVariable(BufferedWriter bw, List<Field> fields, List<String> variable) {
        try {
            // 写入类内容
            for (Field field : fields) {
                List<String> temp = new ArrayList<>(variable);


                if (field.getDataType().equals("Date")) {
                    temp.add(variable.size() - 2, "\t@JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"GMT+8\")");
                    temp.add(variable.size() - 2, "\t@DateTimeFormat(pattern = \"yyyy-MM-dd\")");
                }

                String classVariable = Tools.replaceData(field, temp);

                bw.write(classVariable);
                bw.newLine();
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 写入方法
     * @param bw
     * @param fields
     * @param methods
     */
    @Override
    protected void writeMethod(BufferedWriter bw, List<Field> fields, List<String> methods) {
        try {
            // 写入类方法
            for (Field field : fields) {
                List<String> method = new ArrayList<>(methods);
                String classMethod = Tools.replaceData(field, method);

                bw.write(classMethod);
                bw.newLine();
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
