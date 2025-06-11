package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.pojo.Fiction_typePojo;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.service.Fiction_typeService;
import com.qinge.backend.mapper.Fiction_typeMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class Fiction_typeServiceImpl implements Fiction_typeService<Fiction_typePojo>  {
	@Resource
	private Fiction_typeMapper fiction_typeMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<Fiction_typePojo> select(Query<Fiction_typePojo> query) {
		return fiction_typeMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<Fiction_typePojo> query) {
		fiction_typeMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<Fiction_typePojo> query, Fiction_typePojo np) {
		fiction_typeMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<Fiction_typePojo> query) {
		fiction_typeMapper.delete(query) ;
	}


}