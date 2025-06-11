package com.qinge.backend.service.Impl;
import com.qinge.backend.service.User_contactService;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.User_contactMapper;
import com.qinge.backend.entity.pojo.User_contactPojo;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class User_contactServiceImpl implements User_contactService<User_contactPojo>  {
	@Resource
	private User_contactMapper user_contactMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<User_contactPojo> select(Query<User_contactPojo> query) {
		return user_contactMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<User_contactPojo> query) {
		user_contactMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<User_contactPojo> query, User_contactPojo np) {
		user_contactMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<User_contactPojo> query) {
		user_contactMapper.delete(query) ;
	}


}