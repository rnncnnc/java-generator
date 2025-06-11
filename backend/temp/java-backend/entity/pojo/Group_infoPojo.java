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
@Table(name = "Group_info")
@Schema(name = "Group_info", description = "")
public class Group_infoPojo  {


	/**
	* @Description: 
	*/
	@Column(name = "group_id")
	@Schema(name = "group_id", description = "")
	@Id
	private String group_id;


	/**
	* @Description: 
	*/
	@Column(name = "create_time")
	@Schema(name = "create_time", description = "")
	private Long create_time;


	/**
	* @Description: 
	*/
	@Column(name = "group_name")
	@Schema(name = "group_name", description = "")
	private String group_name;


	/**
	* @Description: 
	*/
	@Column(name = "group_notice")
	@Schema(name = "group_notice", description = "")
	private String group_notice;


	/**
	* @Description: 
	*/
	@Column(name = "group_owner_id")
	@Schema(name = "group_owner_id", description = "")
	private String group_owner_id;


	/**
	* @Description: 
	*/
	@Column(name = "join_type")
	@Schema(name = "join_type", description = "")
	private Integer join_type;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	public Group_infoPojo() {
	}

	public Group_infoPojo(String group_id, Long create_time, String group_name, String group_notice, String group_owner_id, Integer join_type, Integer status) {
		this.group_id = group_id;
		this.create_time = create_time;
		this.group_name = group_name;
		this.group_notice = group_notice;
		this.group_owner_id = group_owner_id;
		this.join_type = join_type;
		this.status = status;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getGroup_id() {
		return this.group_id;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public Long getCreate_time() {
		return this.create_time;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getGroup_name() {
		return this.group_name;
	}

	public void setGroup_notice(String group_notice) {
		this.group_notice = group_notice;
	}

	public String getGroup_notice() {
		return this.group_notice;
	}

	public void setGroup_owner_id(String group_owner_id) {
		this.group_owner_id = group_owner_id;
	}

	public String getGroup_owner_id() {
		return this.group_owner_id;
	}

	public void setJoin_type(Integer join_type) {
		this.join_type = join_type;
	}

	public Integer getJoin_type() {
		return this.join_type;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
}