package com.qinge.backend.entity.enums;

/**
 * @Data: 2025/5/30 18:44
 * @Author: Lc
 * @Description:
 */


public enum DataFields {

    MYSQL8X("Name", "Comment", "Field", "Type", "Comment", "Key", "Key_name", "Column_name"),
    MYSQL5X("TABLE_NAME", "TABLE_COMMENT", "COLUMN_NAME", "COLUMN_TYPE", "COLUMN_COMMENT", "COLUMN_KEY", "INDEX_NAME", "COLUMN_NAME"),
    POSTGRESQL("table_name", "table_comment", "column_name", "data_type", "column_comment", "column_key", "index_name", "column_name");

    private String TableName;
    private String TableComment;
    private String ColumnName;
    private String ColumnType;
    private String ColumnComment;
    private String ColumnKey;
    private String IndexName;
    private String indexColumnName;


    DataFields(String tableName, String tableComment, String columnName, String columnType, String columnComment, String columnKey, String indexName, String indexColumnName) {
        TableName = tableName;
        TableComment = tableComment;
        ColumnName = columnName;
        ColumnType = columnType;
        ColumnComment = columnComment;
        ColumnKey = columnKey;
        IndexName = indexName;
        this.indexColumnName = indexColumnName;
    }

    public String getTableName() {
        return TableName;
    }

    public String getTableComment() {
        return TableComment;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public String getColumnType() {
        return ColumnType;
    }

    public String getColumnComment() {
        return ColumnComment;
    }

    public String getColumnKey() {
        return ColumnKey;
    }

    public String getIndexName() {
        return IndexName;
    }

    public String getIndexColumnName() {
        return indexColumnName;
    }
}
