package com.qinge.backend.service.Impl;
import com.qinge.backend.service.Chat_sessionService;
import com.qinge.backend.mapper.Chat_sessionMapper;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.entity.pojo.Chat_sessionPojo;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class Chat_sessionServiceImpl implements Chat_sessionService<Chat_sessionPojo>  {
	@Resource
	private Chat_sessionMapper chat_sessionMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<Chat_sessionPojo> select(Query<Chat_sessionPojo> query) {
		return chat_sessionMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<Chat_sessionPojo> query) {
		chat_sessionMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<Chat_sessionPojo> query, Chat_sessionPojo np) {
		chat_sessionMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<Chat_sessionPojo> query) {
		chat_sessionMapper.delete(query) ;
	}


}