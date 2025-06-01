package com.qinge.backend.entity.query;
import java.lang.String;
import java.lang.Integer;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */


public class Sort {

    /**
     * 排序字段    1 表示从查结果的第一个字段开始排序
     */
    private Integer index;

    /**
     * 正序 倒序
     */
    private String order;


    public Sort() {
    }

    public Sort(Integer index, String order) {
        this.index = index;

        this.order = order;

    }

    public Integer getIndex() {
        return index;

    }

    public void setIndex(Integer index) {
        this.index = index;

    }

    public String getOrder() {
        return order;

    }


}