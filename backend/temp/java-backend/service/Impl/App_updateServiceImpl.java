package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.service.App_updateService;
import com.qinge.backend.mapper.App_updateMapper;
import com.qinge.backend.entity.pojo.App_updatePojo;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class App_updateServiceImpl implements App_updateService<App_updatePojo>  {
	@Resource
	private App_updateMapper app_updateMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<App_updatePojo> select(Query<App_updatePojo> query) {
		return app_updateMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<App_updatePojo> query) {
		app_updateMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<App_updatePojo> query, App_updatePojo np) {
		app_updateMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<App_updatePojo> query) {
		app_updateMapper.delete(query) ;
	}


}