package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.entity.pojo.ChatSessionPojo;
import com.qinge.backend.mapper.ChatSessionMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ChatSessionServiceImpl  {
	@Resource
	private ChatSessionMapper chatSessionMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	public List<ChatSessionPojo> select(Query<ChatSessionPojo> query) {
		return chatSessionMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	public void insert(Query<ChatSessionPojo> query) {
		chatSessionMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	public void update(Query<ChatSessionPojo> query, ChatSessionPojo np) {
		chatSessionMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	public void delete(Query<ChatSessionPojo> query) {
		chatSessionMapper.delete(query) ;
	}


}