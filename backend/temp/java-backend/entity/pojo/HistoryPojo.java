package com.qinge.backend.entity.pojo;

import java.lang.Long;
import java.lang.Integer;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;
import jakarta.persistence.Id;


/**
* @Data: 2025/06/11 20:57
 * @Author: Lc
 * @Description:
 */


@Entity
@Table(name = "History")
@Schema(name = "History", description = "历史")
public class HistoryPojo  {


	/**
	* @Description: id
	*/
	@Column(name = "id")
	@Schema(name = "id", description = "id")
	@Id
	private Integer id;


	/**
	* @Description: 小说id
	*/
	@Column(name = "fiction_id")
	@Schema(name = "fiction_id", description = "小说id")
	private Integer fiction_id;


	/**
	* @Description: 
	*/
	@Column(name = "fiction_name")
	@Schema(name = "fiction_name", description = "")
	private String fiction_name;


	/**
	* @Description: 类型id
	*/
	@Column(name = "type_id")
	@Schema(name = "type_id", description = "类型id")
	private Integer type_id;


	/**
	* @Description: 章节id
	*/
	@Column(name = "chapter_id")
	@Schema(name = "chapter_id", description = "章节id")
	private Integer chapter_id;


	/**
	* @Description: 
	*/
	@Column(name = "page")
	@Schema(name = "page", description = "")
	private Integer page;


	/**
	* @Description: 
	*/
	@Column(name = "page_size")
	@Schema(name = "page_size", description = "")
	private Integer page_size;


	/**
	* @Description: 
	*/
	@Column(name = "create_time")
	@Schema(name = "create_time", description = "")
	private Long create_time;


	public HistoryPojo() {
	}

	public HistoryPojo(Integer id, Integer fiction_id, String fiction_name, Integer type_id, Integer chapter_id, Integer page, Integer page_size, Long create_time) {
		this.id = id;
		this.fiction_id = fiction_id;
		this.fiction_name = fiction_name;
		this.type_id = type_id;
		this.chapter_id = chapter_id;
		this.page = page;
		this.page_size = page_size;
		this.create_time = create_time;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setFiction_id(Integer fiction_id) {
		this.fiction_id = fiction_id;
	}

	public Integer getFiction_id() {
		return this.fiction_id;
	}

	public void setFiction_name(String fiction_name) {
		this.fiction_name = fiction_name;
	}

	public String getFiction_name() {
		return this.fiction_name;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public Integer getType_id() {
		return this.type_id;
	}

	public void setChapter_id(Integer chapter_id) {
		this.chapter_id = chapter_id;
	}

	public Integer getChapter_id() {
		return this.chapter_id;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPage() {
		return this.page;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public Integer getPage_size() {
		return this.page_size;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public Long getCreate_time() {
		return this.create_time;
	}
}