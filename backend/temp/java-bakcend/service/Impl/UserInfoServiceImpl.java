package com.qinge.bakcend.service.Impl;
import com.qinge.bakcend.mapper.UserInfoMapper;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.entity.pojo.UserInfoPojo;
import com.qinge.bakcend.service.UserInfoService;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserInfoServiceImpl implements UserInfoService<UserInfoPojo>  {
	@Resource
	private UserInfoMapper userInfoMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<UserInfoPojo> select(Query<UserInfoPojo> query) {
		return userInfoMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<UserInfoPojo> query) {
		userInfoMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<UserInfoPojo> query, UserInfoPojo np) {
		userInfoMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<UserInfoPojo> query) {
		userInfoMapper.delete(query) ;
	}


}