package com.qinge.backend.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qinge.backend.connector.MysqlConnector;
import com.qinge.backend.connector.PostgresqlConnector;
import com.qinge.backend.entity.constants.DBConstant;
import com.qinge.backend.entity.constants.MySqlConstant;
import com.qinge.backend.entity.dto.BaseInfo;
import com.qinge.backend.entity.dto.Field;
import com.qinge.backend.entity.dto.Index;
import com.qinge.backend.entity.dto.Table;
import com.qinge.backend.entity.enums.DataFields;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Data: 2025/5/30 13:09
 * @Author: Lc
 * @Description:
 */


public class DataBaseParser {

    /**
     * 从mysql数据库中解析出指定表的信息
     * @return
     */
    public static List<Table> parseMySQLTable(BaseInfo baseInfo) {
        List<Table> tableList = new ArrayList<>();


        // 从 MySQL 数据库获取表结构
        MysqlConnector mysqlConnector = new MysqlConnector(
                baseInfo.getDbUrl(),
                baseInfo.getUsername(),
                baseInfo.getPassword()
        );

        List<Map<String, Object>> version = mysqlConnector.executeQuery("SELECT VERSION();");
        String ver = (String) version.get(0).get(DBConstant.VERSION);
        DataFields dbEnum = null;
        if (ver.startsWith("5")) {
            dbEnum = DataFields.MYSQL5X;
        } else if (ver.startsWith("8")) {
            dbEnum = DataFields.MYSQL8X;
        }


        // 获取数据库内所有表信息
        List<Map<String, Object>> tables = mysqlConnector.executeQuery("show table status;");

        for (Map<String, Object> table : tables) {

            // 存储表信息
            Table tab = new Table();

            String tabName = ((String) table.get(dbEnum.getTableName())).replace(baseInfo.getTablePrefix(), "");
            String classTabName = tabName;
            // 替换掉表前缀
            if (baseInfo.getTablePrefix() != null) {
                classTabName = Tools.toCamel(tabName, baseInfo.getFieldSeparator());
                classTabName = Tools.firstToUppercase(classTabName);
            }

            tab.setTableName(classTabName);


            tab.setTableComment((String) table.get(dbEnum.getTableComment()));

            // 获取表的字段信息
            List<Map<String, Object>> fields = mysqlConnector.executeQuery("SHOW FULL FIELDS FROM " + tabName);

            List<Field> fieldList = new ArrayList<>();
            Set<String> importList = new HashSet<>();
            for (Map<String, Object> field : fields) {
                Field fed = new Field();

                // 设置原始字段名
                String columnName = (String) field.get(dbEnum.getColumnName());
                fed.setColumnName(columnName);

                // 将字段名改为驼峰显示
                String fieldName = Tools.toCamel((columnName), baseInfo.getFieldSeparator());
                fed.setFieldName(fieldName);
                fed.setFieldComment((String) field.get(dbEnum.getColumnComment()));

                // 去除括号  int(8)
                String dataType = (String) field.get(dbEnum.getColumnType());
                String[] split = dataType.split("\\(");
                dataType = split[0];

                // 将mysql的数据类型转为java数据类型，并添加全类名
                if (Tools.contains(MySqlConstant.SQL_DATE_TYPE, dataType)) {
                    dataType = "Date";
                    importList.add(Date.class.getName());
                    importList.add(JsonFormat.class.getName());
                    importList.add(DateTimeFormat.class.getName());
                } else if (Tools.contains(MySqlConstant.SQL_DATE_TIME_TYPE, dataType)) {
                    dataType = "Long";
                    importList.add(Long.class.getName());
                } else if (Tools.contains(MySqlConstant.SQL_DECIMAL_TYPE, dataType)) {
                    dataType = "BigDecimal";
                    importList.add(BigDecimal.class.getName());
                } else if (Tools.contains(MySqlConstant.SQL_STRING_TYPES, dataType)) {
                    dataType = "String";
                    importList.add(String.class.getName());
                } else if (Tools.contains(MySqlConstant.SQL_INTEGER_TYPES, dataType)) {
                    dataType = "Integer";
                    importList.add(Integer.class.getName());
                } else if (Tools.contains(MySqlConstant.SQL_LONG_TYPES, dataType)) {
                    dataType = "Long";
                    importList.add(Long.class.getName());
                }

                fed.setDataType(dataType);

                fed.setKey((String) field.get(dbEnum.getColumnKey()));

                fieldList.add(fed);
            }

            tab.setFields(fieldList);
            tab.setImportList(importList);

            // 获取表的索引信息
            List<Map<String, Object>> indexs = mysqlConnector.executeQuery("show index from " + tabName);

            List<Index> indexList = new ArrayList<>();
            for (Map<String, Object> index : indexs) {
                Index ind = new Index();
                ind.setName((String) index.get("Key_name"));

                ind.setFields(Tools.toCamel((String) index.get("Column_name"), baseInfo.getFieldSeparator()));

                indexList.add(ind);
            }

            tab.setIndexs(indexList);

            tableList.add(tab);
        }

        return tableList;
    }

    /**
     * 从postgresql数据库中解析出指定表的信息
     * @return
     */
    public static List<Table> parsePostgreSQLTable(BaseInfo baseInfo) {
        List<Table> tableList = new ArrayList<>();

        PostgresqlConnector postgresqlConnector = new PostgresqlConnector(
                baseInfo.getDbUrl(),
                baseInfo.getUsername(),
                baseInfo.getPassword()
        );
        // 获取表信息
        // 替换为 PostgreSQL 获取表注释的 SQL（查询 public 模式下的表注释）
        List<Map<String, Object>> tables = postgresqlConnector.executeQuery(
                "SELECT c.relname AS table_name, d.description AS table_comment " +
                        "FROM pg_class c " +
                        "LEFT JOIN pg_namespace n ON n.oid = c.relnamespace " +
                        "LEFT JOIN pg_description d ON d.objoid = c.oid AND d.objsubid = 0 " +
                        "WHERE n.nspname = 'public' AND c.relkind = 'r'"
        );

        DataFields postgresql = DataFields.POSTGRESQL;

        for (Map<String, Object> table : tables) {
            Table tab = new Table();
            tab.setTableName((String) table.get(postgresql.getTableName()));
            tab.setTableComment((String) table.get(postgresql.getTableComment()));

            // 获取字段信息
            List<Map<String, Object>> fields = postgresqlConnector.executeQuery("SELECT " +
                    "    c.column_name, " +
                    "    c.data_type, " +
                    "    pgd.description AS column_comment, " +
                    "    cc.pk_name AS column_key " +
                    "FROM " +
                    "    information_schema.columns c " +
                    "LEFT JOIN ( " +
                    "    SELECT  " +
                    "        con.conname AS pk_name, " +
                    "        att.attname AS column_name, " +
                    "        unnest(con.conkey) AS attnum " +
                    "    FROM  " +
                    "        pg_catalog.pg_constraint con " +
                    "    JOIN  " +
                    "        pg_catalog.pg_class rel ON con.conrelid = rel.oid " +
                    "    JOIN  " +
                    "        pg_catalog.pg_attribute att ON att.attrelid = rel.oid AND att.attnum = ANY(con.conkey) " +
                    "    WHERE  " +
                    "        con.contype = 'p' " +
                    "        AND rel.relname = '" + tab.getTableName() + "' " +
                    ") cc ON c.column_name = cc.column_name " +
                    "LEFT JOIN ( " +
                    "    SELECT  " +
                    "        att.attrelid, " +
                    "        att.attnum, " +
                    "        att.attname, " +
                    "        des.description " +
                    "    FROM  " +
                    "        pg_catalog.pg_attribute att " +
                    "    LEFT JOIN  " +
                    "        pg_catalog.pg_description des ON att.attrelid = des.objoid AND att.attnum = des.objsubid " +
                    "    WHERE  " +
                    "        att.attnum > 0 " +
                    "        AND NOT att.attisdropped " +
                    ") pgd ON c.column_name = pgd.attname AND c.table_name::regclass = pgd.attrelid " +
                    "WHERE " +
                    "    c.table_schema = 'public' " +
                    "    AND c.table_name = '" + tab.getTableName() + "';");

            List<Field> fieldList = new ArrayList<>();
            for (Map<String, Object> field : fields) {
                Field fed = new Field();
                fed.setFieldName((String) field.get(postgresql.getColumnName()));
                fed.setFieldComment((String) field.get(postgresql.getColumnComment()));
                fed.setDataType((String) field.get(postgresql.getColumnType()));
                fed.setKey((String) field.get(postgresql.getColumnKey()));

                fieldList.add(fed);
            }

            tab.setFields(fieldList);

            // 获取索引信息
            List<Map<String, Object>> indexs = postgresqlConnector.executeQuery(
                    "SELECT " +
                            "   indexname AS index_name, " +        // 索引名
                            "   regexp_replace(indexdef, '^CREATE (UNIQUE )?INDEX .+ ON .+ USING .+ \\((.+)\\)$', '\\2') AS column_name " +  // 提取索引字段
                            "FROM pg_indexes " +
                            "WHERE tablename = '" + tab.getTableName() + "' " +  // 当前表名
                            "  AND schemaname = 'public'"               // public模式
            );

            List<Index> indexList = new ArrayList<>();

            for (Map<String, Object> index : indexs) {
                Index ind = new Index();
                ind.setName((String) index.get("index_name"));
                ind.setFields((String) index.get("column_name"));

                indexList.add(ind);
            }

            tab.setIndexs(indexList);
            tableList.add(tab);
        }


        return tableList;
    }


}
