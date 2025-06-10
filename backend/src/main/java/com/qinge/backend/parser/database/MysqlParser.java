package com.qinge.backend.parser.database;

import com.qinge.backend.entity.table.Table;
import com.qinge.backend.entity.enums.DataFields;

import java.util.List;
import java.util.Map;

/**
 * @Data: 2025/6/10 20:28
 * @Author: Lc
 * @Description:
 */


public class MysqlParser extends DBParser {

    @Override
    public List<Table> parseTableFromDB() {
        // 获取数据库枚举变量
        getDataFields();

        return getTables("show table status;",
                "SHOW FULL FIELDS FROM %s",
                "show index from %s");
    }

    // 获取数据库枚举
    @Override
    protected void getDataFields() {
        List<Map<String, Object>> version = connector.executeQuery("SELECT VERSION();");
        String ver = (String) version.get(0).get("VERSION()");
        if (ver.startsWith("5")) {
            dbFields = DataFields.MYSQL5X;
        } else if (ver.startsWith("8")) {
            dbFields = DataFields.MYSQL8X;
        }
    }

}
