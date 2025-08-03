package com.qinge.backend.service;

import com.qinge.backend.dto.DateBaseInfo;
import com.qinge.backend.entity.table.Table;

import java.util.List;

/**
 * @Data: 2025/8/3 11:37
 * @Author: Lc
 * @Description:
 */


public interface TableService {

    /**
     * 获取数据库表列表
     * @param baseInfo
     * @return
     */
    List<Table> getTableList(DateBaseInfo baseInfo) throws Exception;
}
