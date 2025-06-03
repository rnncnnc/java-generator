package com.qinge.backend.builder;

import com.qinge.backend.entity.dto.template.java.JavaClass;

/**
 * @Data: 2025/6/1 16:36
 * @Author: Lc
 * @Description:
 */


public class JavaBuilder extends FileBuilder<JavaClass> {


    @Override
    public void build(JavaClass template) {
        System.out.println("java builder");
    }

    public JavaBuilder() {
    }

    public JavaBuilder(String basePackage, String temPath) {
        super(basePackage, temPath);
    }


}
