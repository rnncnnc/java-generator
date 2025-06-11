package com.qinge.bakcend.entity.pojo;

import java.lang.Integer;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Boolean;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;
import jakarta.persistence.Id;


/**
* @Data: 2025/06/11 21:33
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
	@Schema(name = "fictionId", description = "小说id")
	private Integer fictionId;


	/**
	* @Description: 类型id
	*/
	@Column(name = "type_id")
	@Schema(name = "typeId", description = "类型id")
	private Integer typeId;


	/**
	* @Description: 是否已经阅读
	*/
	@Column(name = "is_read")
	@Schema(name = "isRead", description = "是否已经阅读")
	private Boolean isRead;


	/**
	* @Description: 是否删除
	*/
	@Column(name = "is_delete")
	@Schema(name = "isDelete", description = "是否删除")
	private Boolean isDelete;


	public ChapterPojo() {
	}

	public ChapterPojo(Integer id, String title, Integer fictionId, Integer typeId, Boolean isRead, Boolean isDelete) {
		this.id = id;
		this.title = title;
		this.fictionId = fictionId;
		this.typeId = typeId;
		this.isRead = isRead;
		this.isDelete = isDelete;
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

	public void setFictionId(Integer fictionId) {
		this.fictionId = fictionId;
	}

	public Integer getFictionId() {
		return this.fictionId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Boolean getIsRead() {
		return this.isRead;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Boolean getIsDelete() {
		return this.isDelete;
	}
}