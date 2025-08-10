package com.qinge.backend.parser.database;

import com.qinge.backend.entity.table.Table;
import com.qinge.backend.entity.enums.DataFields;

import java.util.List;

/**
 * @Data: 2025/6/10 21:11
 * @Author: Lc
 * @Description:
 */


public class PostgresqlParser extends DBParser {


    @Override
    public List<Table> parseTableFromDB() {
        // 获取数据库枚举变量
        getDataFields();

        // 表信息sql
        String tableSql = "SELECT c.relname AS table_name, d.description AS table_comment " +
                "FROM pg_class c " +
                "LEFT JOIN pg_namespace n ON n.oid = c.relnamespace " +
                "LEFT JOIN pg_description d ON d.objoid = c.oid AND d.objsubid = 0 " +
                "WHERE n.nspname = 'public' AND c.relkind = 'r'";

        // 字段信息sql
        String fieldSql = "SELECT\n" +
                "  cols.column_name AS \"column_name\",\n" +
                "  format_type(a.atttypid, a.atttypmod) AS \"data_type\",\n" +
                "  col_description(c.oid, cols.ordinal_position) AS \"column_comment\",\n" +
                "  CASE\n" +
                "    WHEN con.contype = 'p' THEN 'PRIMARY'\n" +
                "    WHEN con.contype = 'u' THEN 'UNIQUE'\n" +
                "    WHEN idx.indisunique THEN 'UNIQUE INDEX'\n" +
                "    ELSE 'INDEX'\n" +
                "  END AS \"column_key\"\n" +
                "FROM\n" +
                "  information_schema.columns cols\n" +
                "  JOIN pg_attribute a ON a.attname = cols.column_name\n" +
                "    AND a.attrelid = (cols.table_schema || '.' || cols.table_name)::regclass\n" +
                "  JOIN pg_class c ON c.oid = a.attrelid\n" +
                "  LEFT JOIN pg_index idx ON idx.indrelid = c.oid AND a.attnum = ANY(idx.indkey)\n" +
                "  LEFT JOIN pg_constraint con ON con.conrelid = c.oid AND con.conindid = idx.indexrelid\n" +
                "WHERE\n" +
                "  cols.table_name = '%s'\t\n" +
                "ORDER BY\n" +
                "  cols.ordinal_position;";

        // 索引信息sql
        String indexSql = "SELECT\n" +
                "    CASE c.contype \n" +
                "        WHEN 'p' THEN 'PRIMARY KEY'\n" +
                "        WHEN 'u' THEN 'UNIQUE'\n" +
                "        ELSE 'NORMAL'\n" +
                "    END AS index_name,\n" +
                "\t\ta.attname AS column_name\n" +
                "FROM\n" +
                "    pg_class t\n" +
                "    JOIN pg_index i ON t.oid = i.indrelid\n" +
                "    LEFT JOIN pg_constraint c ON i.indexrelid = c.conindid\n" +
                "    CROSS JOIN LATERAL unnest(i.indkey) AS unnest(attnum) \n" +
                "    JOIN pg_attribute a ON a.attrelid = t.oid AND a.attnum = unnest.attnum \n" +
                "WHERE\n" +
                "    t.relname = '%s'\n" +
                "    AND t.relkind = 'r'\n" +
                "ORDER BY\n" +
                "    index_name, a.attnum;";

        return getTables(tableSql,
                fieldSql,
                indexSql);
    }

    /**
     * 获取数据库枚举
     */
    @Override
    protected void getDataFields() {
        dbFields = DataFields.POSTGRESQL;
    }

}
