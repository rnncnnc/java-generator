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
@Table(name = "UserInfo")
@Schema(name = "UserInfo", description = "用户信息表")
public class UserInfoPojo  {


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
	* @Description: 昵称
	*/
	@Column(name = "nick_name")
	@Schema(name = "nickName", description = "昵称")
	private varchar nickName;


	/**
	* @Description: 0：直接加入   1：同意后加好友
	*/
	@Column(name = "join_type")
	@Schema(name = "joinType", description = "0：直接加入   1：同意后加好友")
	private tinyint joinType;


	/**
	* @Description: 性别 0：女  1：男
	*/
	@Column(name = "sex")
	@Schema(name = "sex", description = "性别 0：女  1：男")
	private tinyint sex;


	/**
	* @Description: 密码
	*/
	@Column(name = "password")
	@Schema(name = "password", description = "密码")
	private varchar password;


	/**
	* @Description: 个性签名
	*/
	@Column(name = "personal_signature")
	@Schema(name = "personalSignature", description = "个性签名")
	private varchar personalSignature;


	/**
	* @Description: 状态
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "状态")
	private tinyint status;


	/**
	* @Description: 创建时间
	*/
	@Column(name = "create_time")
	@Schema(name = "createTime", description = "创建时间")
	private datetime createTime;


	/**
	* @Description: 最后登录时间
	*/
	@Column(name = "last_login_time")
	@Schema(name = "lastLoginTime", description = "最后登录时间")
	private datetime lastLoginTime;


	/**
	* @Description: 最后离开时间
	*/
	@Column(name = "last_off_time")
	@Schema(name = "lastOffTime", description = "最后离开时间")
	private bigint lastOffTime;


	/**
	* @Description: 地区
	*/
	@Column(name = "area_name")
	@Schema(name = "areaName", description = "地区")
	private varchar areaName;


	/**
	* @Description: 地区编号
	*/
	@Column(name = "area_code")
	@Schema(name = "areaCode", description = "地区编号")
	private varchar areaCode;


	public UserInfoPojo() {
	}

	public UserInfoPojo(varchar email, varchar userId, varchar nickName, tinyint joinType, tinyint sex, varchar password, varchar personalSignature, tinyint status, datetime createTime, datetime lastLoginTime, bigint lastOffTime, varchar areaName, varchar areaCode) {
		this.email = email
		this.userId = userId
		this.nickName = nickName
		this.joinType = joinType
		this.sex = sex
		this.password = password
		this.personalSignature = personalSignature
		this.status = status
		this.createTime = createTime
		this.lastLoginTime = lastLoginTime
		this.lastOffTime = lastOffTime
		this.areaName = areaName
		this.areaCode = areaCode
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

	public void setNickName(varchar nickName) {
		this.nickName = nickName;
	}

	public varchar getNickName() {
		return this.nickName
	}

	public void setJoinType(tinyint joinType) {
		this.joinType = joinType;
	}

	public tinyint getJoinType() {
		return this.joinType
	}

	public void setSex(tinyint sex) {
		this.sex = sex;
	}

	public tinyint getSex() {
		return this.sex
	}

	public void setPassword(varchar password) {
		this.password = password;
	}

	public varchar getPassword() {
		return this.password
	}

	public void setPersonalSignature(varchar personalSignature) {
		this.personalSignature = personalSignature;
	}

	public varchar getPersonalSignature() {
		return this.personalSignature
	}

	public void setStatus(tinyint status) {
		this.status = status;
	}

	public tinyint getStatus() {
		return this.status
	}

	public void setCreateTime(datetime createTime) {
		this.createTime = createTime;
	}

	public datetime getCreateTime() {
		return this.createTime
	}

	public void setLastLoginTime(datetime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public datetime getLastLoginTime() {
		return this.lastLoginTime
	}

	public void setLastOffTime(bigint lastOffTime) {
		this.lastOffTime = lastOffTime;
	}

	public bigint getLastOffTime() {
		return this.lastOffTime
	}

	public void setAreaName(varchar areaName) {
		this.areaName = areaName;
	}

	public varchar getAreaName() {
		return this.areaName
	}

	public void setAreaCode(varchar areaCode) {
		this.areaCode = areaCode;
	}

	public varchar getAreaCode() {
		return this.areaCode
	}
}