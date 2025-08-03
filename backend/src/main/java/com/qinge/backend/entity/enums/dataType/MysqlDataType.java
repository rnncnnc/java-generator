package com.qinge.backend.entity.enums.dataType;

import java.util.Arrays;
import java.util.List;

/**
 * @Data: 2025/6/10 22:40
 * @Author: Lc
 * @Description:
 */


public enum MysqlDataType {
    DATETIME("Date", Arrays.asList("date", "datetime", "timestamp")),
    BIGDECIMAL("BigDecimal", Arrays.asList("decimal", "double", "float")),
    STRING("String", Arrays.asList("char", "varchar", "text", "meidumtext", "longtext")),
    INTEGER("Integer", Arrays.asList("int", "tinyint")),
    LONG("Long", Arrays.asList("bigint")),
    BOOLEAN("Boolean", Arrays.asList("boolean", "bit"))
    ;


    private String javaType;

    private List<String> dbType;

    MysqlDataType(String javaType, List<String> dbType) {
        this.javaType = javaType;
        this.dbType = dbType;
    }

    public static String getByType(String type) {
        for (MysqlDataType value : MysqlDataType.values()) {
            if (value.getDbType().contains(type)) {
                return value.getJavaType();
            }
        }
        return null;
    }

    public String getJavaType() {
        return javaType;
    }

    public List<String> getDbType() {
        return dbType;
    }
}
