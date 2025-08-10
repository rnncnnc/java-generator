package com.qinge.backend.builder;

import com.qinge.backend.entity.template.object.FileObject;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @Data: 2025/6/1 14:16
 * @Author: Lc
 * @Description:
 */


public interface Builder {


    /**
     * 构建文件
     * @param fileObj
     */
    String build(FileObject fileObj) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException;
}
