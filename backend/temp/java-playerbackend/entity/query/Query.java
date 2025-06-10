package com.qinge.playerbackend.entity.query;
import com.qinge.playerbackend.entity.query.Sort;
import com.qinge.playerbackend.entity.query.Page;



public class Query<T>   {


	/**
	 * 分页参数
	 */
	private Page page;


	/**
	 * 排序字段
	 */
	private Sort sort;


	/**
	 * 查询条件
	 */
	private T pojo;


	public Query() {
	}

	public Query(Page page, Sort sort, T pojo) {
		this.page = page;
		this.sort = sort;
		this.pojo = pojo;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Page getPage() {
		return this.page;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public Sort getSort() {
		return this.sort;
	}

	public void setPojo(T pojo) {
		this.pojo = pojo;
	}

	public T getPojo() {
		return this.pojo;
	}
}