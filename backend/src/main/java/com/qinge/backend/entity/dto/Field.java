package com.qinge.backend.entity.dto;

/**
 * @Data: 2025/5/30 12:51
 * @Author: Lc
 * @Description:
 */


public class Field {

    // 字段名
    private String FieldName;

    // 数据库字段名
    private String ColumnName;

    // 字段类型
    private String DataType;

    // 字段属性
    private String Key;

    // 字段注释
    private String FieldComment;

    public Field() {
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getFieldComment() {
        return FieldComment;
    }

    public void setFieldComment(String fieldComment) {
        FieldComment = fieldComment;
    }

    @Override
    public String toString() {
        return "Field{" +
                "FieldName='" + FieldName + '\'' +
                ", DataType='" + DataType + '\'' +
                ", Key='" + Key + '\'' +
                ", FieldComment='" + FieldComment + '\'' +
                '}';
    }
}
