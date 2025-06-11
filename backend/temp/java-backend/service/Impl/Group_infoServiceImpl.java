package com.qinge.backend.service.Impl;
import com.qinge.backend.mapper.Group_infoMapper;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.entity.pojo.Group_infoPojo;
import com.qinge.backend.service.Group_infoService;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class Group_infoServiceImpl implements Group_infoService<Group_infoPojo>  {
	@Resource
	private Group_infoMapper group_infoMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<Group_infoPojo> select(Query<Group_infoPojo> query) {
		return group_infoMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<Group_infoPojo> query) {
		group_infoMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<Group_infoPojo> query, Group_infoPojo np) {
		group_infoMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<Group_infoPojo> query) {
		group_infoMapper.delete(query) ;
	}


}