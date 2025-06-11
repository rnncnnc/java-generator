package com.qinge.backend.entity.pojo;

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
@Table(name = "User_info_beauty")
@Schema(name = "User_info_beauty", description = "")
public class User_info_beautyPojo  {


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
	@Column(name = "email")
	@Schema(name = "email", description = "")
	private String email;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	/**
	* @Description: 
	*/
	@Column(name = "user_id")
	@Schema(name = "user_id", description = "")
	private String user_id;


	public User_info_beautyPojo() {
	}

	public User_info_beautyPojo(Integer id, String email, Integer status, String user_id) {
		this.id = id;
		this.email = email;
		this.status = status;
		this.user_id = user_id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_id() {
		return this.user_id;
	}
}