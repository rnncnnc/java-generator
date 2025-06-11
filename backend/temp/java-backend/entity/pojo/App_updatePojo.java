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
@Table(name = "App_update")
@Schema(name = "App_update", description = "")
public class App_updatePojo  {


	/**
	* @Description: 
	*/
	@Column(name = "id")
	@Schema(name = "id", description = "")
	@Id
	private Integer id;


	/**
	* @Description: 
	*/
	@Column(name = "create_time")
	@Schema(name = "create_time", description = "")
	private Long create_time;


	/**
	* @Description: 
	*/
	@Column(name = "file_type")
	@Schema(name = "file_type", description = "")
	private Integer file_type;


	/**
	* @Description: 
	*/
	@Column(name = "grayscale_uid")
	@Schema(name = "grayscale_uid", description = "")
	private String grayscale_uid;


	/**
	* @Description: 
	*/
	@Column(name = "outer_link")
	@Schema(name = "outer_link", description = "")
	private String outer_link;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	/**
	* @Description: 
	*/
	@Column(name = "update_desc")
	@Schema(name = "update_desc", description = "")
	private String update_desc;


	/**
	* @Description: 
	*/
	@Column(name = "version")
	@Schema(name = "version", description = "")
	private String version;


	public App_updatePojo() {
	}

	public App_updatePojo(Integer id, Long create_time, Integer file_type, String grayscale_uid, String outer_link, Integer status, String update_desc, String version) {
		this.id = id;
		this.create_time = create_time;
		this.file_type = file_type;
		this.grayscale_uid = grayscale_uid;
		this.outer_link = outer_link;
		this.status = status;
		this.update_desc = update_desc;
		this.version = version;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public Long getCreate_time() {
		return this.create_time;
	}

	public void setFile_type(Integer file_type) {
		this.file_type = file_type;
	}

	public Integer getFile_type() {
		return this.file_type;
	}

	public void setGrayscale_uid(String grayscale_uid) {
		this.grayscale_uid = grayscale_uid;
	}

	public String getGrayscale_uid() {
		return this.grayscale_uid;
	}

	public void setOuter_link(String outer_link) {
		this.outer_link = outer_link;
	}

	public String getOuter_link() {
		return this.outer_link;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setUpdate_desc(String update_desc) {
		this.update_desc = update_desc;
	}

	public String getUpdate_desc() {
		return this.update_desc;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return this.version;
	}
}