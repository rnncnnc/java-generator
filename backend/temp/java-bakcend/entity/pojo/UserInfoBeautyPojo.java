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
@Table(name = "UserInfoBeauty")
@Schema(name = "UserInfoBeauty", description = "")
public class UserInfoBeautyPojo  {


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
	@Schema(name = "userId", description = "")
	private String userId;


	public UserInfoBeautyPojo() {
	}

	public UserInfoBeautyPojo(Integer id, String email, Integer status, String userId) {
		this.id = id;
		this.email = email;
		this.status = status;
		this.userId = userId;
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

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}
}