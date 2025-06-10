package com.qinge.backend.connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Data: 2025/5/30 12:47
 * @Author: Lc
 * @Description:
 */


public abstract class DBConnector {

    // 数据库连接 URL，需替换为实际的数据库名、主机和端口  "jdbc:postgresql://localhost:5432/your_database_name"
    protected String url;
    // 数据库用户名，需替换为实际用户名
    protected String username;
    // 数据库密码，需替换为实际密码
    protected String password;

    public DBConnector() {
    }

    public DBConnector(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public abstract Connection getConnection(String url, String username, String password);

    // 执行查询语句并返回结果集
    public List<Map<String, Object>> executeQuery(String sql) {
        List<Map<String, Object>> result = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection(url, username, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                result.add(row);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (stmt!= null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if (conn!= null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }
}
