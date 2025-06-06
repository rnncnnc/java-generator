package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.pojo.UserInfoPojo;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.UserInfoMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserInfoServiceImpl  {
	@Resource
	private UserInfoMapper userInfoMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	public List<UserInfoPojo> select(Query<UserInfoPojo> query) {
		return userInfoMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	public void insert(Query<UserInfoPojo> query) {
		userInfoMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	public void update(Query<UserInfoPojo> query, UserInfoPojo np) {
		userInfoMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	public void delete(Query<UserInfoPojo> query) {
		userInfoMapper.delete(query) ;
	}


}