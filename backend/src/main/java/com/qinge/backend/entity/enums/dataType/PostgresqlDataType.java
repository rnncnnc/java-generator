package com.qinge.backend.entity.enums.dataType;

import java.util.Arrays;
import java.util.List;

/**
 * @Data: 2025/6/10 23:38
 * @Author: Lc
 * @Description:
 */


public enum PostgresqlDataType {
    LOCALDATETIME("Date", Arrays.asList("date", "timestamp without time zone", "timestamp", "timestamptz")),
    BIGDECIMAL("BigDecimal", Arrays.asList("numeric", "real", "double precision")),
    STRING("String", Arrays.asList("char", "varchar", "text", "bpchar", "citext", "character varying")),
    INTEGER("Integer", Arrays.asList("int", "integer", "smallint", "int2", "int4")),
    BIGINTEGER("BigInteger", Arrays.asList("bigint", "int8")),
    BOOLEAN("Boolean", Arrays.asList("boolean", "bool")),
    BLOB("Blob", Arrays.asList("bytea"))
    ;


    private String javaType;

    private List<String> dbType;

    PostgresqlDataType(String javaType, List<String> dbType) {
        this.javaType = javaType;
        this.dbType = dbType;
    }

    public static String getByType(String type) {
        for (PostgresqlDataType value : PostgresqlDataType.values()) {
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
