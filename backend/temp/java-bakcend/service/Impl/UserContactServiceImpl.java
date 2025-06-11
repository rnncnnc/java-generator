package com.qinge.bakcend.service.Impl;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.service.UserContactService;
import com.qinge.bakcend.entity.pojo.UserContactPojo;
import com.qinge.bakcend.mapper.UserContactMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserContactServiceImpl implements UserContactService<UserContactPojo>  {
	@Resource
	private UserContactMapper userContactMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<UserContactPojo> select(Query<UserContactPojo> query) {
		return userContactMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<UserContactPojo> query) {
		userContactMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<UserContactPojo> query, UserContactPojo np) {
		userContactMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<UserContactPojo> query) {
		userContactMapper.delete(query) ;
	}


}