package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.pojo.GroupInfoPojo;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.GroupInfoMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class GroupInfoServiceImpl  {
	@Resource
	private GroupInfoMapper groupInfoMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	public List<GroupInfoPojo> select(Query<GroupInfoPojo> query) {
		return groupInfoMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	public void insert(Query<GroupInfoPojo> query) {
		groupInfoMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	public void update(Query<GroupInfoPojo> query, GroupInfoPojo np) {
		groupInfoMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	public void delete(Query<GroupInfoPojo> query) {
		groupInfoMapper.delete(query) ;
	}


}