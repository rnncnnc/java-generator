package com.qinge.backend.service;

import java.lang.reflect.InvocationTargetException;

/**
 * @Data: 2025/5/30 15:13
 * @Author: Lc
 * @Description:
 */


public interface BuilderService {

    // 构建文件
    public void buildFile() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
