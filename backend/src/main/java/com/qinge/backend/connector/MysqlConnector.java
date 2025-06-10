package com.qinge.backend.connector;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Data: 2025/5/30 13:05
 * @Author: Lc
 * @Description:
 */


public class MysqlConnector extends DBConnector{


    @Override
    public Connection getConnection(String url, String username, String password) {
        Connection conn = null;
        try {
            // 加载 MySql JDBC 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 建立数据库连接
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
