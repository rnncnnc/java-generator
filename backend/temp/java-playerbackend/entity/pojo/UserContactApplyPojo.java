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
@Table(name = "UserContactApply")
@Schema(name = "UserContactApply", description = "联系人申请")
public class UserContactApplyPojo  {


	/**
	* @Description: 自增ID
	*/
	@Column(name = "apply_id")
	@Schema(name = "applyId", description = "自增ID")
	@Id
	private Integer applyId;


	/**
	* @Description: 
	*/
	@Column(name = "apply_user_id")
	@Schema(name = "applyUserId", description = "")
	private String applyUserId;


	/**
	* @Description: 
	*/
	@Column(name = "receive_user_id")
	@Schema(name = "receiveUserId", description = "")
	private String receiveUserId;


	/**
	* @Description: 
	*/
	@Column(name = "contact_type")
	@Schema(name = "contactType", description = "")
	private Integer contactType;


	/**
	* @Description: 
	*/
	@Column(name = "contact_id")
	@Schema(name = "contactId", description = "")
	private String contactId;


	/**
	* @Description: 最后申请时间
	*/
	@Column(name = "last_apply_time")
	@Schema(name = "lastApplyTime", description = "最后申请时间")
	private Long lastApplyTime;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	/**
	* @Description: 
	*/
	@Column(name = "apply_info")
	@Schema(name = "applyInfo", description = "")
	private String applyInfo;


	public UserContactApplyPojo() {
	}

	public UserContactApplyPojo(Integer applyId, String applyUserId, String receiveUserId, Integer contactType, String contactId, Long lastApplyTime, Integer status, String applyInfo) {
		this.applyId = applyId;
		this.applyUserId = applyUserId;
		this.receiveUserId = receiveUserId;
		this.contactType = contactType;
		this.contactId = contactId;
		this.lastApplyTime = lastApplyTime;
		this.status = status;
		this.applyInfo = applyInfo;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public Integer getApplyId() {
		return this.applyId;
	}

	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}

	public String getApplyUserId() {
		return this.applyUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public String getReceiveUserId() {
		return this.receiveUserId;
	}

	public void setContactType(Integer contactType) {
		this.contactType = contactType;
	}

	public Integer getContactType() {
		return this.contactType;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getContactId() {
		return this.contactId;
	}

	public void setLastApplyTime(Long lastApplyTime) {
		this.lastApplyTime = lastApplyTime;
	}

	public Long getLastApplyTime() {
		return this.lastApplyTime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setApplyInfo(String applyInfo) {
		this.applyInfo = applyInfo;
	}

	public String getApplyInfo() {
		return this.applyInfo;
	}
}