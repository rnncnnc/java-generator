package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.pojo.UserContactApplyPojo;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.UserContactApplyMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserContactApplyServiceImpl  {
	@Resource
	private UserContactApplyMapper userContactApplyMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	public List<UserContactApplyPojo> select(Query<UserContactApplyPojo> query) {
		return userContactApplyMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	public void insert(Query<UserContactApplyPojo> query) {
		userContactApplyMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	public void update(Query<UserContactApplyPojo> query, UserContactApplyPojo np) {
		userContactApplyMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	public void delete(Query<UserContactApplyPojo> query) {
		userContactApplyMapper.delete(query) ;
	}


}