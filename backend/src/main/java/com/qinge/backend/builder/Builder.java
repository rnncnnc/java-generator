package com.qinge.backend.builder;

import com.qinge.backend.entity.dto.template.object.FileObject;

import java.io.IOException;

/**
 * @Data: 2025/6/1 14:16
 * @Author: Lc
 * @Description:
 */


public interface Builder<T> {


    /**
     * 构建文件
     * @param fileObj
     */
    void build(FileObject fileObj) throws IOException;
}
