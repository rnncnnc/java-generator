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
@Table(name = "Chapter")
@Schema(name = "Chapter", description = "章节表")
public class ChapterPojo  {


	/**
	* @Description: chap_id
	*/
	@Column(name = "id")
	@Schema(name = "id", description = "chap_id")
	@Id
	private Long id;


	/**
	* @Description: fiction_id
	*/
	@Column(name = "fiction_id")
	@Schema(name = "fictionId", description = "fiction_id")
	private Long fictionId;


	/**
	* @Description: type_id
	*/
	@Column(name = "type_id")
	@Schema(name = "typeId", description = "type_id")
	private Long typeId;


	/**
	* @Description: 章节名
	*/
	@Column(name = "title")
	@Schema(name = "title", description = "章节名")
	private String title;


	/**
	* @Description: 音频保存地址
	*/
	@Column(name = "url")
	@Schema(name = "url", description = "音频保存地址")
	private String url;


	public ChapterPojo() {
	}

	public ChapterPojo(Long id, Long fictionId, Long typeId, String title, String url) {
		this.id = id;
		this.fictionId = fictionId;
		this.typeId = typeId;
		this.title = title;
		this.url = url;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setFictionId(Long fictionId) {
		this.fictionId = fictionId;
	}

	public Long getFictionId() {
		return this.fictionId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}
}