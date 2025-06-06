package com.qinge.backend.service;
import com.qinge.backend.entity.query.Query;

import java.util.List;


public interface UserInfoService<T>   {


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	List<T> select(Query<T> query) {
	}


	/**
	 * 插入数据
	 * @param query
	 */
	void insert(Query<T> query) {
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	void update(Query<T> query, T np) {
	}


	/**
	 * 删除数据
	 * @param query
	 */
	void delete(Query<T> query) {
	}


}