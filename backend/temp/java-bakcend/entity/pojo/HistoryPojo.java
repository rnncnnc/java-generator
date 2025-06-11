package com.qinge.bakcend.entity.pojo;

import java.lang.Long;
import java.lang.Integer;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(name = "fictionId", description = "小说id")
	private Integer fictionId;


	/**
	* @Description: 
	*/
	@Column(name = "fiction_name")
	@Schema(name = "fictionName", description = "")
	private String fictionName;


	/**
	* @Description: 类型id
	*/
	@Column(name = "type_id")
	@Schema(name = "typeId", description = "类型id")
	private Integer typeId;


	/**
	* @Description: 章节id
	*/
	@Column(name = "chapter_id")
	@Schema(name = "chapterId", description = "章节id")
	private Integer chapterId;


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
	@Schema(name = "pageSize", description = "")
	private Integer pageSize;


	/**
	* @Description: 
	*/
	@Column(name = "create_time")
	@Schema(name = "createTime", description = "")
	private Long createTime;


	public HistoryPojo() {
	}

	public HistoryPojo(Integer id, Integer fictionId, String fictionName, Integer typeId, Integer chapterId, Integer page, Integer pageSize, Long createTime) {
		this.id = id;
		this.fictionId = fictionId;
		this.fictionName = fictionName;
		this.typeId = typeId;
		this.chapterId = chapterId;
		this.page = page;
		this.pageSize = pageSize;
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setFictionId(Integer fictionId) {
		this.fictionId = fictionId;
	}

	public Integer getFictionId() {
		return this.fictionId;
	}

	public void setFictionName(String fictionName) {
		this.fictionName = fictionName;
	}

	public String getFictionName() {
		return this.fictionName;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	public Integer getChapterId() {
		return this.chapterId;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPage() {
		return this.page;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}
}