package com.qinge.backend.entity.enums.dataType;

/**
 * @Data: 2025/6/10 23:02
 * @Author: Lc
 * @Description:
 */


public enum DBType {
    MYSQL("Mysql", MysqlDataType.class),
    POSTGRESQL("Postgresql", PostgresqlDataType.class)
    ;


    private String dbType;

    private Class<?> clazz;

    DBType(String dbType, Class<?> clazz) {
        this.dbType = dbType;
        this.clazz = clazz;
    }

    public static Class<?> getClassByType(String type) {
        for (DBType value : DBType.values()) {
            if (value.getDbType().equals(type)) {
                return value.getClazz();
            }
        }

        return null;
    }

    public String getDbType() {
        return dbType;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
