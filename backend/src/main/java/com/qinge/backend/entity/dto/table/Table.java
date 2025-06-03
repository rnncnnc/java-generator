package com.qinge.backend.entity.dto.table;

import com.qinge.backend.utils.StringTools;

import java.util.List;
import java.util.Set;

/**
 * @Data: 2025/5/30 12:49
 * @Author: Lc
 * @Description:
 */


public class Table {

    // 表名
    private String TableName;

    // 表注释
    private String TableComment;

    // 字段信息
    private List<Field> Fields;

    // 索引信息
    private List<Index> Indexs;

    // 导入包的集合
    private Set<String> importList;

    public Table() {
    }

    public Set<String> getImportList() {
        return importList;
    }

    public void setImportList(Set<String> importList) {
        this.importList = importList;
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
                ", TableComment='" + TableComment + '\'' +
                ", Fields=" + Fields +
                ", Indexs=" + Indexs +
                ", importList=" + importList +
                '}';
    }
}
