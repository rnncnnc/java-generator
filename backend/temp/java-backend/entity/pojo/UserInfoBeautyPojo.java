package com.qinge.backend.entity.pojo;

import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;


/**
* @Data: 2025/06/07 01:59
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
	private int id;


	/**
	* @Description: 邮箱
	*/
	@Column(name = "email")
	@Schema(name = "email", description = "邮箱")
	private varchar email;


	/**
	* @Description: 用户id
	*/
	@Column(name = "user_id")
	@Schema(name = "userId", description = "用户id")
	private varchar userId;


	/**
	* @Description: 0：未使用   1：已使用
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "0：未使用   1：已使用")
	private tinyint status;


	public UserInfoBeautyPojo() {
	}

	public UserInfoBeautyPojo(int id, varchar email, varchar userId, tinyint status) {
		this.id = id
		this.email = email
		this.userId = userId
		this.status = status
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id
	}

	public void setEmail(varchar email) {
		this.email = email;
	}

	public varchar getEmail() {
		return this.email
	}

	public void setUserId(varchar userId) {
		this.userId = userId;
	}

	public varchar getUserId() {
		return this.userId
	}

	public void setStatus(tinyint status) {
		this.status = status;
	}

	public tinyint getStatus() {
		return this.status
	}
}