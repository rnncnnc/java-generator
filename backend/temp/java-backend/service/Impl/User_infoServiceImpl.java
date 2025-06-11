package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.pojo.User_infoPojo;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.User_infoMapper;
import com.qinge.backend.service.User_infoService;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class User_infoServiceImpl implements User_infoService<User_infoPojo>  {
	@Resource
	private User_infoMapper user_infoMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<User_infoPojo> select(Query<User_infoPojo> query) {
		return user_infoMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<User_infoPojo> query) {
		user_infoMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<User_infoPojo> query, User_infoPojo np) {
		user_infoMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<User_infoPojo> query) {
		user_infoMapper.delete(query) ;
	}


}