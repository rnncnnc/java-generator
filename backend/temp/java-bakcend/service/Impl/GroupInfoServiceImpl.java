package com.qinge.bakcend.service.Impl;
import com.qinge.bakcend.service.GroupInfoService;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.entity.pojo.GroupInfoPojo;
import com.qinge.bakcend.mapper.GroupInfoMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class GroupInfoServiceImpl implements GroupInfoService<GroupInfoPojo>  {
	@Resource
	private GroupInfoMapper groupInfoMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<GroupInfoPojo> select(Query<GroupInfoPojo> query) {
		return groupInfoMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<GroupInfoPojo> query) {
		groupInfoMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<GroupInfoPojo> query, GroupInfoPojo np) {
		groupInfoMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<GroupInfoPojo> query) {
		groupInfoMapper.delete(query) ;
	}


}