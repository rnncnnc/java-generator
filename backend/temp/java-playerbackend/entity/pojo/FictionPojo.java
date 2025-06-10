package com.qinge.playerbackend.entity.pojo;

import java.lang.Long;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;
import jakarta.persistence.Id;


/**
* @Data: 2025/06/11 00:11
 * @Author: Lc
 * @Description:
 */


@Entity
@Table(name = "Fiction")
@Schema(name = "Fiction", description = "")
public class FictionPojo  {


	/**
	* @Description: fiction_id
	*/
	@Column(name = "id")
	@Schema(name = "id", description = "fiction_id")
	@Id
	private Long id;


	/**
	* @Description: 小说名字
	*/
	@Column(name = "novel")
	@Schema(name = "novel", description = "小说名字")
	private String novel;


	/**
	* @Description: 小说类型
	*/
	@Column(name = "type")
	@Schema(name = "type", description = "小说类型")
	private String type;


	/**
	* @Description: 小说总章节数
	*/
	@Column(name = "chap_count")
	@Schema(name = "chapCount", description = "小说总章节数")
	private String chapCount;


	public FictionPojo() {
	}

	public FictionPojo(Long id, String novel, String type, String chapCount) {
		this.id = id;
		this.novel = novel;
		this.type = type;
		this.chapCount = chapCount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setNovel(String novel) {
		this.novel = novel;
	}

	public String getNovel() {
		return this.novel;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setChapCount(String chapCount) {
		this.chapCount = chapCount;
	}

	public String getChapCount() {
		return this.chapCount;
	}
}