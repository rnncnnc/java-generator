package com.qinge.bakcend.service.Impl;
import com.qinge.bakcend.service.ChapterService;
import com.qinge.bakcend.entity.pojo.ChapterPojo;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.mapper.ChapterMapper;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class ChapterServiceImpl implements ChapterService<ChapterPojo>  {
	@Resource
	private ChapterMapper chapterMapper;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	 */
	@Override
	public List<ChapterPojo> select(Query<ChapterPojo> query) {
		return chapterMapper.select(query);
	}


	/**
	 * 插入数据
	 * @param query
	 */
	@Override
	public void insert(Query<ChapterPojo> query) {
		chapterMapper.insert(query);
	}


	/**
	 * 更新数据
	 * @param query
	 * @param np
	 */
	@Override
	public void update(Query<ChapterPojo> query, ChapterPojo np) {
		chapterMapper.update(query, np);
	}


	/**
	 * 删除数据
	 * @param query
	 */
	@Override
	public void delete(Query<ChapterPojo> query) {
		chapterMapper.delete(query) ;
	}


}