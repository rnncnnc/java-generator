package com.qinge.backend.entity.dto.table;

/**
 * @Data: 2025/5/30 12:51
 * @Author: Lc
 * @Description:
 */


public class Field {

    // 字段名
    private String FieldName;

    // 数据库字段名
    private String FieldColumn;

    // 字段类型
    private String FieldDataType;

    // 字段属性
    private String FieldKey;

    // 字段注释
    private String FieldComment;

    public Field() {
    }

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }

    public String getFieldColumn() {
        return FieldColumn;
    }

    public void setFieldColumn(String fieldColumn) {
        FieldColumn = fieldColumn;
    }

    public String getFieldDataType() {
        return FieldDataType;
    }

    public void setFieldDataType(String fieldDataType) {
        FieldDataType = fieldDataType;
    }

    public String getFieldKey() {
        return FieldKey;
    }

    public void setFieldKey(String fieldKey) {
        FieldKey = fieldKey;
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
                ", FieldColumn='" + FieldColumn + '\'' +
                ", FieldDataType='" + FieldDataType + '\'' +
                ", FieldKey='" + FieldKey + '\'' +
                ", FieldComment='" + FieldComment + '\'' +
                '}';
    }
}
