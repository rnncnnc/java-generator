package com.qinge.bakcend.service.Impl;
import com.qinge.bakcend.entity.pojo.ChatSessionPojo;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.mapper.ChatSessionMapper;
import com.qinge.bakcend.service.ChatSessionService;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ChatSessionServiceImpl implements ChatSessionService<ChatSessionPojo>  {
	@Resource
	private ChatSessionMapper chatSessionMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<ChatSessionPojo> select(Query<ChatSessionPojo> query) {
		return chatSessionMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<ChatSessionPojo> query) {
		chatSessionMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<ChatSessionPojo> query, ChatSessionPojo np) {
		chatSessionMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<ChatSessionPojo> query) {
		chatSessionMapper.delete(query) ;
	}


}