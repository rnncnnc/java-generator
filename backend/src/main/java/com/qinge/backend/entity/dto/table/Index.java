package com.qinge.backend.entity.dto.table;

import java.util.List;

/**
 * @Data: 2025/5/30 12:55
 * @Author: Lc
 * @Description:
 */


public class Index {

    // 索引名
    private String IndexName;

    // 索引关联字段
    private String IndexField;

    private String IndexColumn;

    public Index() {
    }

    public String getIndexColumn() {
        return IndexColumn;
    }

    public void setIndexColumn(String indexColumn) {
        IndexColumn = indexColumn;
    }

    public String getIndexName() {
        return IndexName;
    }

    public void setIndexName(String indexName) {
        IndexName = indexName;
    }

    public String getIndexField() {
        return IndexField;
    }

    public void setIndexField(String indexField) {
        IndexField = indexField;
    }

    @Override
    public String toString() {
        return "Index{" +
                "IndexName='" + IndexName + '\'' +
                ", IndexField='" + IndexField + '\'' +
                ", IndexColumn='" + IndexColumn + '\'' +
                '}';
    }
}
