package com.qinge.bakcend.entity.pojo;

import java.lang.Long;
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
@Table(name = "UserInfo")
@Schema(name = "UserInfo", description = "")
public class UserInfoPojo  {


	/**
	* @Description: 
	*/
	@Column(name = "user_id")
	@Schema(name = "userId", description = "")
	@Id
	private String userId;


	/**
	* @Description: 
	*/
	@Column(name = "area_code")
	@Schema(name = "areaCode", description = "")
	private String areaCode;


	/**
	* @Description: 
	*/
	@Column(name = "area_name")
	@Schema(name = "areaName", description = "")
	private String areaName;


	/**
	* @Description: 
	*/
	@Column(name = "create_time")
	@Schema(name = "createTime", description = "")
	private Long createTime;


	/**
	* @Description: 
	*/
	@Column(name = "email")
	@Schema(name = "email", description = "")
	private String email;


	/**
	* @Description: 
	*/
	@Column(name = "join_type")
	@Schema(name = "joinType", description = "")
	private Integer joinType;


	/**
	* @Description: 
	*/
	@Column(name = "last_login_time")
	@Schema(name = "lastLoginTime", description = "")
	private Long lastLoginTime;


	/**
	* @Description: 
	*/
	@Column(name = "last_off_time")
	@Schema(name = "lastOffTime", description = "")
	private Long lastOffTime;


	/**
	* @Description: 
	*/
	@Column(name = "nick_name")
	@Schema(name = "nickName", description = "")
	private String nickName;


	/**
	* @Description: 
	*/
	@Column(name = "password")
	@Schema(name = "password", description = "")
	private String password;


	/**
	* @Description: 
	*/
	@Column(name = "personal_signature")
	@Schema(name = "personalSignature", description = "")
	private String personalSignature;


	/**
	* @Description: 
	*/
	@Column(name = "sex")
	@Schema(name = "sex", description = "")
	private Integer sex;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	public UserInfoPojo() {
	}

	public UserInfoPojo(String userId, String areaCode, String areaName, Long createTime, String email, Integer joinType, Long lastLoginTime, Long lastOffTime, String nickName, String password, String personalSignature, Integer sex, Integer status) {
		this.userId = userId;
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.createTime = createTime;
		this.email = email;
		this.joinType = joinType;
		this.lastLoginTime = lastLoginTime;
		this.lastOffTime = lastOffTime;
		this.nickName = nickName;
		this.password = password;
		this.personalSignature = personalSignature;
		this.sex = sex;
		this.status = status;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setJoinType(Integer joinType) {
		this.joinType = joinType;
	}

	public Integer getJoinType() {
		return this.joinType;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Long getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastOffTime(Long lastOffTime) {
		this.lastOffTime = lastOffTime;
	}

	public Long getLastOffTime() {
		return this.lastOffTime;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPersonalSignature(String personalSignature) {
		this.personalSignature = personalSignature;
	}

	public String getPersonalSignature() {
		return this.personalSignature;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
}