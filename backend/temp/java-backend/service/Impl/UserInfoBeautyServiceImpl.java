package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.pojo.UserInfoBeautyPojo;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.UserInfoBeautyMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserInfoBeautyServiceImpl  {
	@Resource
	private UserInfoBeautyMapper userInfoBeautyMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	public List<UserInfoBeautyPojo> select(Query<UserInfoBeautyPojo> query) {
		return userInfoBeautyMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	public void insert(Query<UserInfoBeautyPojo> query) {
		userInfoBeautyMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	public void update(Query<UserInfoBeautyPojo> query, UserInfoBeautyPojo np) {
		userInfoBeautyMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	public void delete(Query<UserInfoBeautyPojo> query) {
		userInfoBeautyMapper.delete(query) ;
	}


}