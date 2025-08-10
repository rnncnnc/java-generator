package com.qinge.backend.service;

import com.qinge.backend.dto.BaseInfo;

/**
 * @Data: 2025/5/30 15:13
 * @Author: Lc
 * @Description:
 */


public interface BuilderService {

    // 构建文件
    void buildFile(BaseInfo baseInfo) throws Exception;
}
