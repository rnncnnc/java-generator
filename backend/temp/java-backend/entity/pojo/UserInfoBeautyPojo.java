package com.qinge.backend.entity.pojo;

import java.lang.Integer;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;


/**
* @Data: 2025/06/10 16:08
 * @Author: Lc
 * @Description:
 */


@Entity
@Table(name = "UserInfoBeauty")
@Schema(name = "UserInfoBeauty", description = "靓号表")
public class UserInfoBeautyPojo  {


	/**
	* @Description: 自增id
	*/
	@Column(name = "id")
	@Schema(name = "id", description = "自增id")
	private Integer id;


	/**
	* @Description: 邮箱
	*/
	@Column(name = "email")
	@Schema(name = "email", description = "邮箱")
	private String email;


	/**
	* @Description: 用户id
	*/
	@Column(name = "user_id")
	@Schema(name = "userId", description = "用户id")
	private String userId;


	/**
	* @Description: 0：未使用   1：已使用
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "0：未使用   1：已使用")
	private Integer status;


	public UserInfoBeautyPojo() {
	}

	public UserInfoBeautyPojo(Integer id, String email, String userId, Integer status) {
		this.id = id;
		this.email = email;
		this.userId = userId;
		this.status = status;
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

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
}