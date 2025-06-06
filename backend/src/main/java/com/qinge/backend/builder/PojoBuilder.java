package com.qinge.backend.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qinge.backend.entity.dto.table.Field;
import com.qinge.backend.entity.dto.template.object.java.Annotation;
import com.qinge.backend.entity.dto.template.object.java.AnnotationParam;
import com.qinge.backend.entity.dto.template.object.java.JavaClass;
import com.qinge.backend.entity.dto.template.object.java.Variable;
import com.qinge.backend.utils.ClassTools;
import com.qinge.backend.utils.StringTools;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Data: 2025/6/6 11:14
 * @Author: Lc
 * @Description:
 */


@Slf4j
public class PojoBuilder extends JavaBuilder {

    /**
     * 写入内容之前进行的操作
     * @param javaClass
     */
    @Override
    protected JavaClass beforeWrite(JavaClass javaClass) throws JsonProcessingException {
        List<Variable> variables = javaClass.getVariables();
        List<Variable> variableList = new ArrayList<>();

        for (Field field : table.getFields()) {

            for (Variable variable : variables) {
                // 深拷贝变量
                Variable variableCopy = ClassTools.deepCopy(variable);

                variableCopy = replaceKeyword(variableCopy, field);

                // 如果是主键 则添加@Id注解
                // TODO 改进判断主键的方法
                Annotation annotation;
                if (!StringTools.isEmpty(field.getFieldKey()) && field.getFieldKey().equals("PRIMARY")) {
                    annotation = new Annotation();
                    annotation.setName("Id");

                    variableCopy.getAnnotations().add(annotation);
                }

                if (field.getFieldDataType().equals("Date")) {
                    // 添加@JsonFormat注解
                    annotation = new Annotation();
                    annotation.setName("JsonFormat");

                    List<AnnotationParam> annotationParams = new ArrayList<>();

                    // 添加参数
                    AnnotationParam annotationParam = new AnnotationParam();
                    annotationParam.setKey("pattern");
                    annotationParam.setValue("yyyy-MM-dd HH:mm:ss");
                    annotationParams.add(annotationParam);

                    annotationParam = new AnnotationParam();
                    annotationParam.setKey("timezone");
                    annotationParam.setValue("GMT+8");

                    annotationParams.add(annotationParam);
                    annotation.setParams(annotationParams);

                    variableCopy.getAnnotations().add(annotation);

                    // 添加@DateTimeFormat注解
                    annotation = new Annotation();
                    annotation.setName("DateTimeFormat");

                    annotationParams = new ArrayList<>();

                    // 添加参数
                    annotationParam = new AnnotationParam();
                    annotationParam.setKey("pattern");
                    annotationParam.setValue("yyyy-MM-dd");

                    annotationParams.add(annotationParam);
                    annotation.setParams(annotationParams);

                    variableCopy.getAnnotations().add(annotation);
                }

                // 将变量添加到列表中
                variableList.add(variableCopy);
            }
        }

        // 深拷贝类
        JavaClass javaClassUsed = ClassTools.deepCopy(javaClass);

        // 设置变量列表
        javaClassUsed.setVariables(variableList);

        // 返回替换关键词后的类
        return replaceKeyword(javaClassUsed, table);
    }
}
