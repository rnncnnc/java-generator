package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.pojo.User_contact_applyPojo;
import com.qinge.backend.service.User_contact_applyService;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.User_contact_applyMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class User_contact_applyServiceImpl implements User_contact_applyService<User_contact_applyPojo>  {
	@Resource
	private User_contact_applyMapper user_contact_applyMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<User_contact_applyPojo> select(Query<User_contact_applyPojo> query) {
		return user_contact_applyMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<User_contact_applyPojo> query) {
		user_contact_applyMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<User_contact_applyPojo> query, User_contact_applyPojo np) {
		user_contact_applyMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<User_contact_applyPojo> query) {
		user_contact_applyMapper.delete(query) ;
	}


}