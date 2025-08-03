package com.qinge.backend.dto;

/**
 * @Data: 2025/5/30 19:06
 * @Author: Lc
 * @Description: 数据库基本信息
 */


public class DateBaseInfo {

    // 数据库类型
    private String dbType;

    // 数据库连接地址
    private String dbUrl;

    // 数据库用户名
    private String username;

    // 数据库密码
    private String password;

    // 临时目录
    private String tempPath;

    // 表前缀
    private String tablePrefix;

    // 字段中间的分隔符
    private String fieldSeparator;

    public DateBaseInfo() {
    }

    public String getFieldSeparator() {
        return fieldSeparator;
    }

    public void setFieldSeparator(String fieldSeparator) {
        this.fieldSeparator = fieldSeparator;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }
}
