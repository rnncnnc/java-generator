package com.qinge.playerbackend.entity.pojo;

import java.lang.Long;
import java.lang.Integer;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;
import jakarta.persistence.Id;


/**
* @Data: 2025/06/10 18:54
 * @Author: Lc
 * @Description:
 */


@Entity
@Table(name = "UserContact")
@Schema(name = "UserContact", description = "联系人")
public class UserContactPojo  {


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
	@Column(name = "contact_id")
	@Schema(name = "contactId", description = "")
	@Id
	private String contactId;


	/**
	* @Description: 
	*/
	@Column(name = "contact_type")
	@Schema(name = "contactType", description = "")
	private Integer contactType;


	/**
	* @Description: 
	*/
	@Column(name = "create_time")
	@Schema(name = "createTime", description = "")
	private Long createTime;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	/**
	* @Description: 
	*/
	@Column(name = "last_update_time")
	@Schema(name = "lastUpdateTime", description = "")
	private Long lastUpdateTime;


	public UserContactPojo() {
	}

	public UserContactPojo(String userId, String contactId, Integer contactType, Long createTime, Integer status, Long lastUpdateTime) {
		this.userId = userId;
		this.contactId = contactId;
		this.contactType = contactType;
		this.createTime = createTime;
		this.status = status;
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getContactId() {
		return this.contactId;
	}

	public void setContactType(Integer contactType) {
		this.contactType = contactType;
	}

	public Integer getContactType() {
		return this.contactType;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Long getLastUpdateTime() {
		return this.lastUpdateTime;
	}
}