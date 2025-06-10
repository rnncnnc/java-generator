package com.qinge.playerbackend.service.Impl;
import com.qinge.playerbackend.entity.query.Query;
import com.qinge.playerbackend.service.UserContactApplyService;
import com.qinge.playerbackend.mapper.UserContactApplyMapper;
import com.qinge.playerbackend.entity.pojo.UserContactApplyPojo;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class UserContactApplyServiceImpl implements UserContactApplyService<UserContactApplyPojo>  {
	@Resource
	private UserContactApplyMapper userContactApplyMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<UserContactApplyPojo> select(Query<UserContactApplyPojo> query) {
		return userContactApplyMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<UserContactApplyPojo> query) {
		userContactApplyMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<UserContactApplyPojo> query, UserContactApplyPojo np) {
		userContactApplyMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<UserContactApplyPojo> query) {
		userContactApplyMapper.delete(query) ;
	}


}