package com.qinge.backend.service.Impl;
import com.qinge.backend.service.User_info_beautyService;
import com.qinge.backend.entity.pojo.User_info_beautyPojo;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.User_info_beautyMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class User_info_beautyServiceImpl implements User_info_beautyService<User_info_beautyPojo>  {
	@Resource
	private User_info_beautyMapper user_info_beautyMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<User_info_beautyPojo> select(Query<User_info_beautyPojo> query) {
		return user_info_beautyMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<User_info_beautyPojo> query) {
		user_info_beautyMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<User_info_beautyPojo> query, User_info_beautyPojo np) {
		user_info_beautyMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<User_info_beautyPojo> query) {
		user_info_beautyMapper.delete(query) ;
	}


}