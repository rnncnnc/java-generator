package com.qinge.playerbackend.service.Impl;
import com.qinge.playerbackend.entity.pojo.FictionPojo;
import com.qinge.playerbackend.service.FictionService;
import com.qinge.playerbackend.entity.query.Query;
import com.qinge.playerbackend.mapper.FictionMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class FictionServiceImpl implements FictionService<FictionPojo>  {
	@Resource
	private FictionMapper fictionMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<FictionPojo> select(Query<FictionPojo> query) {
		return fictionMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<FictionPojo> query) {
		fictionMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<FictionPojo> query, FictionPojo np) {
		fictionMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<FictionPojo> query) {
		fictionMapper.delete(query) ;
	}


}