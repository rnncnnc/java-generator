package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.pojo.UserContactPojo;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.UserContactMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserContactServiceImpl  {
	@Resource
	private UserContactMapper userContactMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	public List<UserContactPojo> select(Query<UserContactPojo> query) {
		return userContactMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	public void insert(Query<UserContactPojo> query) {
		userContactMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	public void update(Query<UserContactPojo> query, UserContactPojo np) {
		userContactMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	public void delete(Query<UserContactPojo> query) {
		userContactMapper.delete(query) ;
	}


}