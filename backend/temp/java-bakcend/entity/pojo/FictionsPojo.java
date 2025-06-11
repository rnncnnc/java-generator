package com.qinge.bakcend.entity.pojo;

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
@Table(name = "Fictions")
@Schema(name = "Fictions", description = "小说")
public class FictionsPojo  {


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
	@Column(name = "name")
	@Schema(name = "name", description = "")
	private String name;


	/**
	* @Description: 
	*/
	@Column(name = "path")
	@Schema(name = "path", description = "")
	private String path;


	/**
	* @Description: 类型id
	*/
	@Column(name = "type_id")
	@Schema(name = "typeId", description = "类型id")
	private Integer typeId;


	/**
	* @Description: 
	*/
	@Column(name = "url")
	@Schema(name = "url", description = "")
	private String url;


	/**
	* @Description: 小说爬取次数
	*/
	@Column(name = "times")
	@Schema(name = "times", description = "小说爬取次数")
	private Integer times;


	public FictionsPojo() {
	}

	public FictionsPojo(Integer id, String name, String path, Integer typeId, String url, Integer times) {
		this.id = id;
		this.name = name;
		this.path = path;
		this.typeId = typeId;
		this.url = url;
		this.times = times;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getTimes() {
		return this.times;
	}
}