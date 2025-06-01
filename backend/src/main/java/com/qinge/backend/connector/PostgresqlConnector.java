package com.qinge.backend.connector;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Data: 2025/5/30 13:02
 * @Author: Lc
 * @Description:
 */


public class PostgresqlConnector extends DBConnector{

    public PostgresqlConnector(String db_url, String username, String password) {
        super(db_url, username, password);
    }

    @Override
    public Connection getConnection() {
        Connection conn = null;
        try {
            // 加载 PostgreSQL JDBC 驱动
            Class.forName("org.postgresql.Driver");
            // 建立数据库连接
            conn = DriverManager.getConnection(db_url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
