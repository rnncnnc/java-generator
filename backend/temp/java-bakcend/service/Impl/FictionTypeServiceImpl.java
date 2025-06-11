package com.qinge.bakcend.service.Impl;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.entity.pojo.FictionTypePojo;
import com.qinge.bakcend.service.FictionTypeService;
import com.qinge.bakcend.mapper.FictionTypeMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class FictionTypeServiceImpl implements FictionTypeService<FictionTypePojo>  {
	@Resource
	private FictionTypeMapper fictionTypeMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<FictionTypePojo> select(Query<FictionTypePojo> query) {
		return fictionTypeMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<FictionTypePojo> query) {
		fictionTypeMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<FictionTypePojo> query, FictionTypePojo np) {
		fictionTypeMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<FictionTypePojo> query) {
		fictionTypeMapper.delete(query) ;
	}


}