package com.qinge.bakcend.service.Impl;
import com.qinge.bakcend.service.UserInfoBeautyService;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.entity.pojo.UserInfoBeautyPojo;
import com.qinge.bakcend.mapper.UserInfoBeautyMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserInfoBeautyServiceImpl implements UserInfoBeautyService<UserInfoBeautyPojo>  {
	@Resource
	private UserInfoBeautyMapper userInfoBeautyMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<UserInfoBeautyPojo> select(Query<UserInfoBeautyPojo> query) {
		return userInfoBeautyMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<UserInfoBeautyPojo> query) {
		userInfoBeautyMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<UserInfoBeautyPojo> query, UserInfoBeautyPojo np) {
		userInfoBeautyMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<UserInfoBeautyPojo> query) {
		userInfoBeautyMapper.delete(query) ;
	}


}