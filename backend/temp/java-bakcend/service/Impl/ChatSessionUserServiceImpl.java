package com.qinge.bakcend.service.Impl;
import com.qinge.bakcend.entity.pojo.ChatSessionUserPojo;
import com.qinge.bakcend.mapper.ChatSessionUserMapper;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.service.ChatSessionUserService;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ChatSessionUserServiceImpl implements ChatSessionUserService<ChatSessionUserPojo>  {
	@Resource
	private ChatSessionUserMapper chatSessionUserMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<ChatSessionUserPojo> select(Query<ChatSessionUserPojo> query) {
		return chatSessionUserMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<ChatSessionUserPojo> query) {
		chatSessionUserMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<ChatSessionUserPojo> query, ChatSessionUserPojo np) {
		chatSessionUserMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<ChatSessionUserPojo> query) {
		chatSessionUserMapper.delete(query) ;
	}


}