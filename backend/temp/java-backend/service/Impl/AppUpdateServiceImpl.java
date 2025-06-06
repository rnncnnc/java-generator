package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.AppUpdateMapper;
import com.qinge.backend.entity.pojo.AppUpdatePojo;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class AppUpdateServiceImpl  {
	@Resource
	private AppUpdateMapper appUpdateMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	public List<AppUpdatePojo> select(Query<AppUpdatePojo> query) {
		return appUpdateMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	public void insert(Query<AppUpdatePojo> query) {
		appUpdateMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	public void update(Query<AppUpdatePojo> query, AppUpdatePojo np) {
		appUpdateMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	public void delete(Query<AppUpdatePojo> query) {
		appUpdateMapper.delete(query) ;
	}


}