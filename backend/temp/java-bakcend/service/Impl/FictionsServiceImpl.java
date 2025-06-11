package com.qinge.bakcend.service.Impl;
import com.qinge.bakcend.entity.pojo.FictionsPojo;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.service.FictionsService;
import com.qinge.bakcend.mapper.FictionsMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class FictionsServiceImpl implements FictionsService<FictionsPojo>  {
	@Resource
	private FictionsMapper fictionsMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<FictionsPojo> select(Query<FictionsPojo> query) {
		return fictionsMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<FictionsPojo> query) {
		fictionsMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<FictionsPojo> query, FictionsPojo np) {
		fictionsMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<FictionsPojo> query) {
		fictionsMapper.delete(query) ;
	}


}