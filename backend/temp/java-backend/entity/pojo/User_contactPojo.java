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
@Table(name = "User_contact")
@Schema(name = "User_contact", description = "")
public class User_contactPojo  {


	/**
	* @Description: 
	*/
	@Column(name = "user_id")
	@Schema(name = "user_id", description = "")
	@Id
	private String user_id;


	/**
	* @Description: 
	*/
	@Column(name = "contact_id")
	@Schema(name = "contact_id", description = "")
	@Id
	private String contact_id;


	/**
	* @Description: 
	*/
	@Column(name = "contact_type")
	@Schema(name = "contact_type", description = "")
	private Integer contact_type;


	/**
	* @Description: 
	*/
	@Column(name = "create_time")
	@Schema(name = "create_time", description = "")
	private Long create_time;


	/**
	* @Description: 
	*/
	@Column(name = "last_update_time")
	@Schema(name = "last_update_time", description = "")
	private Long last_update_time;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	public User_contactPojo() {
	}

	public User_contactPojo(String user_id, String contact_id, Integer contact_type, Long create_time, Long last_update_time, Integer status) {
		this.user_id = user_id;
		this.contact_id = contact_id;
		this.contact_type = contact_type;
		this.create_time = create_time;
		this.last_update_time = last_update_time;
		this.status = status;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_id() {
		return this.user_id;
	}

	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}

	public String getContact_id() {
		return this.contact_id;
	}

	public void setContact_type(Integer contact_type) {
		this.contact_type = contact_type;
	}

	public Integer getContact_type() {
		return this.contact_type;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public Long getCreate_time() {
		return this.create_time;
	}

	public void setLast_update_time(Long last_update_time) {
		this.last_update_time = last_update_time;
	}

	public Long getLast_update_time() {
		return this.last_update_time;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
}