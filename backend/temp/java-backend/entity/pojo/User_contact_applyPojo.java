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
@Table(name = "User_contact_apply")
@Schema(name = "User_contact_apply", description = "")
public class User_contact_applyPojo  {


	/**
	* @Description: 
	*/
	@Column(name = "apply_id")
	@Schema(name = "apply_id", description = "")
	@Id
	private Integer apply_id;


	/**
	* @Description: 
	*/
	@Column(name = "apply_info")
	@Schema(name = "apply_info", description = "")
	private String apply_info;


	/**
	* @Description: 
	*/
	@Column(name = "apply_user_id")
	@Schema(name = "apply_user_id", description = "")
	private String apply_user_id;


	/**
	* @Description: 
	*/
	@Column(name = "contact_id")
	@Schema(name = "contact_id", description = "")
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
	@Column(name = "last_apply_time")
	@Schema(name = "last_apply_time", description = "")
	private Long last_apply_time;


	/**
	* @Description: 
	*/
	@Column(name = "receive_user_id")
	@Schema(name = "receive_user_id", description = "")
	private String receive_user_id;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	public User_contact_applyPojo() {
	}

	public User_contact_applyPojo(Integer apply_id, String apply_info, String apply_user_id, String contact_id, Integer contact_type, Long last_apply_time, String receive_user_id, Integer status) {
		this.apply_id = apply_id;
		this.apply_info = apply_info;
		this.apply_user_id = apply_user_id;
		this.contact_id = contact_id;
		this.contact_type = contact_type;
		this.last_apply_time = last_apply_time;
		this.receive_user_id = receive_user_id;
		this.status = status;
	}

	public void setApply_id(Integer apply_id) {
		this.apply_id = apply_id;
	}

	public Integer getApply_id() {
		return this.apply_id;
	}

	public void setApply_info(String apply_info) {
		this.apply_info = apply_info;
	}

	public String getApply_info() {
		return this.apply_info;
	}

	public void setApply_user_id(String apply_user_id) {
		this.apply_user_id = apply_user_id;
	}

	public String getApply_user_id() {
		return this.apply_user_id;
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

	public void setLast_apply_time(Long last_apply_time) {
		this.last_apply_time = last_apply_time;
	}

	public Long getLast_apply_time() {
		return this.last_apply_time;
	}

	public void setReceive_user_id(String receive_user_id) {
		this.receive_user_id = receive_user_id;
	}

	public String getReceive_user_id() {
		return this.receive_user_id;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
}