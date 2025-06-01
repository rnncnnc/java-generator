package com.qinge.backend.entity.query;
import com.qinge.backend.entity.query.Sort;
import com.qinge.backend.entity.query.Page;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



public class Query<T> {

    /**
     * 分页参数
     */
    private Page page;

    /**
     * 排序字段
     */
    private Sort sort;

    /**
     * 查询条件
     */
    private T pojo;


    public Query() {
    }

    public Query(Page page, Sort sort, T pojo) {
        this.page = page;

        this.sort = sort;

        this.pojo = pojo;

    }

    public Page getPage() {
        return page;

    }

    public void setPage(Page page) {
        this.page = page;

    }

    public Sort getOrder() {
        return sort;

    }

    public void setOrder(Sort sort) {
        this.sort = sort;

    }

    public T getPojo() {
        return pojo;

    }

    public void setPojo(T pojo) {
        this.pojo = pojo;

    }


}