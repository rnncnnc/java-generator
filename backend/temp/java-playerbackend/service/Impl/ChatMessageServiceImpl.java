package com.qinge.playerbackend.service.Impl;
import com.qinge.playerbackend.entity.pojo.ChatMessagePojo;
import com.qinge.playerbackend.entity.query.Query;
import com.qinge.playerbackend.mapper.ChatMessageMapper;
import com.qinge.playerbackend.service.ChatMessageService;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ChatMessageServiceImpl implements ChatMessageService<ChatMessagePojo>  {
	@Resource
	private ChatMessageMapper chatMessageMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<ChatMessagePojo> select(Query<ChatMessagePojo> query) {
		return chatMessageMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<ChatMessagePojo> query) {
		chatMessageMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<ChatMessagePojo> query, ChatMessagePojo np) {
		chatMessageMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<ChatMessagePojo> query) {
		chatMessageMapper.delete(query) ;
	}


}