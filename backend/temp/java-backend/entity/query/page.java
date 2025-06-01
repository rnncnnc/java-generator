package com.qinge.backend.entity.query;
import java.lang.Integer;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



public class Page {

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;


    public Page() {
    }

    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;

        this.pageSize = pageSize;

    }

    public Integer getPageNum() {
        return pageNum;

    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;

    }

    public Integer getPageSize() {
        return pageSize;

    }


}