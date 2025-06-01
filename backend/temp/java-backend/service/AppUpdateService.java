package com.qinge.backend.service;
import java.util.List;
import com.qinge.backend.entity.query.Query;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



public interface AppUpdateService<T> {

    /**
     * 查找符合类型的所有数据
     * @param query
     * @return
     */
    List<T> select(Query<T> query);

    /**
     * 插入数据
     * @param query
     */
    void insert(Query<T> query);

    /**
     * 删除数据
     * @param query
     */
    void delete(Query<T> query);

    /**
     * 更新数据
     * @param query
     * @param np
     */
    void update(Query<T> query, T np);


}