package com.qinge.backend.entity.query;

import java.lang.Integer;


public class Page  {


	/**
	 * 页码
	 */
	private Integer pageNum;;


	/**
	 * 每页大小
	 */
	private Integer pageSize;


	public Page() {
	}

	public Page(Integer pageNum;, Integer pageSize) {
		this.pageNum; = pageNum;;
		this.pageSize = pageSize;
	}

	public void setPageNum;(Integer pageNum;) {
		this.pageNum; = pageNum;;
	}

	public Integer getPageNum;() {
		return this.pageNum;;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}
}