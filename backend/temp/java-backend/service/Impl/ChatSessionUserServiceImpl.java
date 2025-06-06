package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.entity.pojo.ChatSessionUserPojo;
import com.qinge.backend.mapper.ChatSessionUserMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ChatSessionUserServiceImpl  {
	@Resource
	private ChatSessionUserMapper chatSessionUserMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	public List<ChatSessionUserPojo> select(Query<ChatSessionUserPojo> query) {
		return chatSessionUserMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	public void insert(Query<ChatSessionUserPojo> query) {
		chatSessionUserMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	public void update(Query<ChatSessionUserPojo> query, ChatSessionUserPojo np) {
		chatSessionUserMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	public void delete(Query<ChatSessionUserPojo> query) {
		chatSessionUserMapper.delete(query) ;
	}


}