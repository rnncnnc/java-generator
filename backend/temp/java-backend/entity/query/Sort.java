package com.qinge.backend.entity.query;

import java.lang.Integer;
import java.lang.String;


public class Sort  {


	/**
	 * 排序字段 1 表示从查结果的第一个字段开始排序
	 */
	private Integer index;


	/**
	 * 正序倒序 asc desc
	 */
	private String order;


	public Sort() {
	}

	public Sort(Integer index, String order) {
		this.index = index;
		this.order = order;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getIndex() {
		return this.index;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrder() {
		return this.order;
	}
}