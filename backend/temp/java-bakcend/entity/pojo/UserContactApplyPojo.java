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
@Table(name = "UserContactApply")
@Schema(name = "UserContactApply", description = "")
public class UserContactApplyPojo  {


	/**
	* @Description: 
	*/
	@Column(name = "apply_id")
	@Schema(name = "applyId", description = "")
	@Id
	private Integer applyId;


	/**
	* @Description: 
	*/
	@Column(name = "apply_info")
	@Schema(name = "applyInfo", description = "")
	private String applyInfo;


	/**
	* @Description: 
	*/
	@Column(name = "apply_user_id")
	@Schema(name = "applyUserId", description = "")
	private String applyUserId;


	/**
	* @Description: 
	*/
	@Column(name = "contact_id")
	@Schema(name = "contactId", description = "")
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
	@Column(name = "last_apply_time")
	@Schema(name = "lastApplyTime", description = "")
	private Long lastApplyTime;


	/**
	* @Description: 
	*/
	@Column(name = "receive_user_id")
	@Schema(name = "receiveUserId", description = "")
	private String receiveUserId;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	public UserContactApplyPojo() {
	}

	public UserContactApplyPojo(Integer applyId, String applyInfo, String applyUserId, String contactId, Integer contactType, Long lastApplyTime, String receiveUserId, Integer status) {
		this.applyId = applyId;
		this.applyInfo = applyInfo;
		this.applyUserId = applyUserId;
		this.contactId = contactId;
		this.contactType = contactType;
		this.lastApplyTime = lastApplyTime;
		this.receiveUserId = receiveUserId;
		this.status = status;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public Integer getApplyId() {
		return this.applyId;
	}

	public void setApplyInfo(String applyInfo) {
		this.applyInfo = applyInfo;
	}

	public String getApplyInfo() {
		return this.applyInfo;
	}

	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}

	public String getApplyUserId() {
		return this.applyUserId;
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

	public void setLastApplyTime(Long lastApplyTime) {
		this.lastApplyTime = lastApplyTime;
	}

	public Long getLastApplyTime() {
		return this.lastApplyTime;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getReceiveUserId() {
		return this.receiveUserId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
}