package com.qinge.backend.service;

import com.qinge.backend.entity.BaseInfo;

/**
 * @Data: 2025/5/30 15:13
 * @Author: Lc
 * @Description:
 */


public interface BuilderService {

    // 构建文件
    public void buildFile(BaseInfo baseInfo) throws Exception;
}
