package com.qinge.backend.entity.pojo;

import java.lang.Integer;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Boolean;
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
@Table(name = "Chapter")
@Schema(name = "Chapter", description = "章节")
public class ChapterPojo  {


	/**
	* @Description: id
	*/
	@Column(name = "id")
	@Schema(name = "id", description = "id")
	@Id
	private Integer id;


	/**
	* @Description: 
	*/
	@Column(name = "title")
	@Schema(name = "title", description = "")
	private String title;


	/**
	* @Description: 小说id
	*/
	@Column(name = "fiction_id")
	@Schema(name = "fiction_id", description = "小说id")
	private Integer fiction_id;


	/**
	* @Description: 类型id
	*/
	@Column(name = "type_id")
	@Schema(name = "type_id", description = "类型id")
	private Integer type_id;


	/**
	* @Description: 是否已经阅读
	*/
	@Column(name = "is_read")
	@Schema(name = "is_read", description = "是否已经阅读")
	private Boolean is_read;


	/**
	* @Description: 是否删除
	*/
	@Column(name = "is_delete")
	@Schema(name = "is_delete", description = "是否删除")
	private Boolean is_delete;


	public ChapterPojo() {
	}

	public ChapterPojo(Integer id, String title, Integer fiction_id, Integer type_id, Boolean is_read, Boolean is_delete) {
		this.id = id;
		this.title = title;
		this.fiction_id = fiction_id;
		this.type_id = type_id;
		this.is_read = is_read;
		this.is_delete = is_delete;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setFiction_id(Integer fiction_id) {
		this.fiction_id = fiction_id;
	}

	public Integer getFiction_id() {
		return this.fiction_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public Integer getType_id() {
		return this.type_id;
	}

	public void setIs_read(Boolean is_read) {
		this.is_read = is_read;
	}

	public Boolean getIs_read() {
		return this.is_read;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}

	public Boolean getIs_delete() {
		return this.is_delete;
	}
}