package com.qinge.backend.entity.table;

import com.qinge.backend.utils.StringTools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Data: 2025/5/30 12:49
 * @Author: Lc
 * @Description:
 */


public class Table {

    // 表名
    private String TableName;

    // 表名(原始)
    private String TableOriginName;

    // 表注释
    private String TableComment;

    // 基础包名
    private String BasePackage;

    // 字段信息
    private List<Field> Fields;

    // 索引信息
    private List<Index> Indexs;

    public Table() {
    }

    public String getBasePackage() {
        return BasePackage;
    }

    public void setBasePackage(String basePackage) {
        BasePackage = basePackage;
    }

    /**
     * 获取当前时间
     * @return
     */
    public String getDateTime() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date());
    }

    public String getTableName() {
        return TableName;
    }

    public String getLTableName() {
        return StringTools.firstToLowercase(TableName);
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public String getTableOriginName() {
        return TableOriginName;
    }

    public void setTableOriginName(String tableOriginName) {
        TableOriginName = tableOriginName;
    }

    public String getTableComment() {
        return TableComment;
    }

    public void setTableComment(String tableComment) {
        TableComment = tableComment;
    }

    public List<Field> getFields() {
        return Fields;
    }

    public void setFields(List<Field> fields) {
        Fields = fields;
    }

    public List<Index> getIndexs() {
        return Indexs;
    }

    public void setIndexs(List<Index> indexs) {
        Indexs = indexs;
    }

    @Override
    public String toString() {
        return "Table{" +
                "TableName='" + TableName + '\'' +
                ", TableOriginName='" + TableOriginName + '\'' +
                ", TableComment='" + TableComment + '\'' +
                ", Fields=" + Fields +
                ", Indexs=" + Indexs +
                '}';
    }
}
