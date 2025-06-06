package com.qinge.backend.parser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qinge.backend.connector.MysqlConnector;
import com.qinge.backend.connector.PostgresqlConnector;
import com.qinge.backend.entity.constants.DBConstant;
import com.qinge.backend.entity.constants.MySqlConstant;
import com.qinge.backend.entity.dto.BaseInfo;
import com.qinge.backend.entity.dto.table.Field;
import com.qinge.backend.entity.dto.table.Index;
import com.qinge.backend.entity.dto.table.Table;
import com.qinge.backend.entity.enums.DataFields;
import com.qinge.backend.utils.ParserTools;
import com.qinge.backend.utils.StringTools;
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

            tab.setTableOriginName((String) table.get(dbEnum.getTableName()));
            String tabName = ((String) table.get(dbEnum.getTableName())).replace(baseInfo.getTablePrefix(), "");
            String classTabName = tabName;
            // 替换掉表前缀
            if (baseInfo.getTablePrefix() != null) {
                classTabName = StringTools.toCamel(tabName, baseInfo.getFieldSeparator());
                classTabName = StringTools.firstToUppercase(classTabName);
            }

            tab.setTableName(classTabName);

            String tabComment = (String) table.get(dbEnum.getTableComment());
            tab.setTableComment(tabComment.trim());

            // 获取表的字段信息
            List<Map<String, Object>> fields = mysqlConnector.executeQuery("SHOW FULL FIELDS FROM " + tabName);

            List<Field> fieldList = new ArrayList<>();
            for (Map<String, Object> field : fields) {
                Field fed = new Field();

                // 设置原始字段名
                String columnName = (String) field.get(dbEnum.getColumnName());
                fed.setFieldColumn(columnName);

                // 将字段名改为驼峰显示
                String fieldName = StringTools.toCamel(columnName, baseInfo.getFieldSeparator());
                fed.setFieldName(fieldName);

                String fieldComment = (String) field.get(dbEnum.getColumnComment());
                fed.setFieldComment(fieldComment.trim());

                // 去除括号  int(8)
                String dataType = (String) field.get(dbEnum.getColumnType());
                String[] split = dataType.split("\\(");
                dataType = split[0];

                fed.setFieldDataType(dataType);

                fed.setFieldKey((String) field.get(dbEnum.getColumnKey()));

                fieldList.add(fed);
            }

            tab.setFields(fieldList);

            // 获取表的索引信息
            List<Map<String, Object>> indexs = mysqlConnector.executeQuery("show index from " + tabName);

            List<Index> indexList = new ArrayList<>();
            for (Map<String, Object> index : indexs) {
                Index ind = new Index();
                ind.setIndexName((String) index.get(dbEnum.getIndexName()));

                ind.setIndexColumn((String) index.get(dbEnum.getIndexColumnName()));

                ind.setIndexField(StringTools.toCamel((String) index.get(dbEnum.getIndexColumnName()), baseInfo.getFieldSeparator()));

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
                fed.setFieldDataType((String) field.get(postgresql.getColumnType()));
                fed.setFieldKey((String) field.get(postgresql.getColumnKey()));

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
                ind.setIndexName((String) index.get("index_name"));
                ind.setIndexField((String) index.get("column_name"));

                indexList.add(ind);
            }

            tab.setIndexs(indexList);
            tableList.add(tab);
        }


        return tableList;
    }

    public static void main(String[] args) {
        // TODO 移到controller层
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setDbType("mysql");
        baseInfo.setDbUrl("jdbc:mysql://gpa.bakistrim.site:3306/easy_chat");
        baseInfo.setUsername("root");
        baseInfo.setPassword("315766");
        baseInfo.setGroupId("com.qinge");
        baseInfo.setArtifactId("backend");
        baseInfo.setTablePrefix("tb_");
        baseInfo.setFieldSeparator("_");

        List<Table> tables = parseMySQLTable(baseInfo);
        for (Table table : tables) {
            System.out.println(table);
        }
    }

}
