package com.qinge.bakcend.service.Impl;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.entity.pojo.AppUpdatePojo;
import com.qinge.bakcend.service.AppUpdateService;
import com.qinge.bakcend.mapper.AppUpdateMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class AppUpdateServiceImpl implements AppUpdateService<AppUpdatePojo>  {
	@Resource
	private AppUpdateMapper appUpdateMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<AppUpdatePojo> select(Query<AppUpdatePojo> query) {
		return appUpdateMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<AppUpdatePojo> query) {
		appUpdateMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<AppUpdatePojo> query, AppUpdatePojo np) {
		appUpdateMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<AppUpdatePojo> query) {
		appUpdateMapper.delete(query) ;
	}


}