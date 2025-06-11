package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.Chat_messageMapper;
import com.qinge.backend.entity.pojo.Chat_messagePojo;
import com.qinge.backend.service.Chat_messageService;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class Chat_messageServiceImpl implements Chat_messageService<Chat_messagePojo>  {
	@Resource
	private Chat_messageMapper chat_messageMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<Chat_messagePojo> select(Query<Chat_messagePojo> query) {
		return chat_messageMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<Chat_messagePojo> query) {
		chat_messageMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<Chat_messagePojo> query, Chat_messagePojo np) {
		chat_messageMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<Chat_messagePojo> query) {
		chat_messageMapper.delete(query) ;
	}


}