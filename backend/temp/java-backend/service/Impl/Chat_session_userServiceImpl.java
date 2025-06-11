package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.pojo.Chat_session_userPojo;
import com.qinge.backend.service.Chat_session_userService;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.Chat_session_userMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class Chat_session_userServiceImpl implements Chat_session_userService<Chat_session_userPojo>  {
	@Resource
	private Chat_session_userMapper chat_session_userMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<Chat_session_userPojo> select(Query<Chat_session_userPojo> query) {
		return chat_session_userMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<Chat_session_userPojo> query) {
		chat_session_userMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<Chat_session_userPojo> query, Chat_session_userPojo np) {
		chat_session_userMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<Chat_session_userPojo> query) {
		chat_session_userMapper.delete(query) ;
	}


}