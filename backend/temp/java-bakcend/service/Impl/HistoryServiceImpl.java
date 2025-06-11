package com.qinge.bakcend.service.Impl;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.service.HistoryService;
import com.qinge.bakcend.entity.pojo.HistoryPojo;
import com.qinge.bakcend.mapper.HistoryMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class HistoryServiceImpl implements HistoryService<HistoryPojo>  {
	@Resource
	private HistoryMapper historyMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<HistoryPojo> select(Query<HistoryPojo> query) {
		return historyMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<HistoryPojo> query) {
		historyMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<HistoryPojo> query, HistoryPojo np) {
		historyMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<HistoryPojo> query) {
		historyMapper.delete(query) ;
	}


}