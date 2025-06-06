package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.ChatMessageMapper;
import com.qinge.backend.entity.pojo.ChatMessagePojo;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ChatMessageServiceImpl  {
	@Resource
	private ChatMessageMapper chatMessageMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	public List<ChatMessagePojo> select(Query<ChatMessagePojo> query) {
		return chatMessageMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	public void insert(Query<ChatMessagePojo> query) {
		chatMessageMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	public void update(Query<ChatMessagePojo> query, ChatMessagePojo np) {
		chatMessageMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	public void delete(Query<ChatMessagePojo> query) {
		chatMessageMapper.delete(query) ;
	}


}