package com.qinge.backend.entity.pojo;

import java.lang.Long;
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
@Table(name = "UserInfo")
@Schema(name = "UserInfo", description = "用户信息表")
public class UserInfoPojo  {


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
	* @Description: 昵称
	*/
	@Column(name = "nick_name")
	@Schema(name = "nickName", description = "昵称")
	private String nickName;


	/**
	* @Description: 0：直接加入   1：同意后加好友
	*/
	@Column(name = "join_type")
	@Schema(name = "joinType", description = "0：直接加入   1：同意后加好友")
	private Integer joinType;


	/**
	* @Description: 性别 0：女  1：男
	*/
	@Column(name = "sex")
	@Schema(name = "sex", description = "性别 0：女  1：男")
	private Integer sex;


	/**
	* @Description: 密码
	*/
	@Column(name = "password")
	@Schema(name = "password", description = "密码")
	private String password;


	/**
	* @Description: 个性签名
	*/
	@Column(name = "personal_signature")
	@Schema(name = "personalSignature", description = "个性签名")
	private String personalSignature;


	/**
	* @Description: 状态
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "状态")
	private Integer status;


	/**
	* @Description: 创建时间
	*/
	@Column(name = "create_time")
	@Schema(name = "createTime", description = "创建时间")
	private Long createTime;


	/**
	* @Description: 最后登录时间
	*/
	@Column(name = "last_login_time")
	@Schema(name = "lastLoginTime", description = "最后登录时间")
	private Long lastLoginTime;


	/**
	* @Description: 最后离开时间
	*/
	@Column(name = "last_off_time")
	@Schema(name = "lastOffTime", description = "最后离开时间")
	private Long lastOffTime;


	/**
	* @Description: 地区
	*/
	@Column(name = "area_name")
	@Schema(name = "areaName", description = "地区")
	private String areaName;


	/**
	* @Description: 地区编号
	*/
	@Column(name = "area_code")
	@Schema(name = "areaCode", description = "地区编号")
	private String areaCode;


	public UserInfoPojo() {
	}

	public UserInfoPojo(String email, String userId, String nickName, Integer joinType, Integer sex, String password, String personalSignature, Integer status, Long createTime, Long lastLoginTime, Long lastOffTime, String areaName, String areaCode) {
		this.email = email;
		this.userId = userId;
		this.nickName = nickName;
		this.joinType = joinType;
		this.sex = sex;
		this.password = password;
		this.personalSignature = personalSignature;
		this.status = status;
		this.createTime = createTime;
		this.lastLoginTime = lastLoginTime;
		this.lastOffTime = lastOffTime;
		this.areaName = areaName;
		this.areaCode = areaCode;
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

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setJoinType(Integer joinType) {
		this.joinType = joinType;
	}

	public Integer getJoinType() {
		return this.joinType;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
		return this.sex;
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

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
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

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaCode() {
		return this.areaCode;
	}
}