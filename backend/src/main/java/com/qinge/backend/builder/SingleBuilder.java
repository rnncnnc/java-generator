package com.qinge.backend.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qinge.backend.entity.dto.template.object.java.JavaClass;

/**
 * @Data: 2025/6/6 17:47
 * @Author: Lc
 * @Description:
 */


public class SingleBuilder extends JavaBuilder {

    @Override
    protected JavaClass beforeWrite(JavaClass javaClass) throws JsonProcessingException {
        return javaClass;
    }
}
