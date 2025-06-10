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
@Table(name = "UserContactApply")
@Schema(name = "UserContactApply", description = "联系人申请")
public class UserContactApplyPojo  {


	/**
	* @Description: 自增ID
	*/
	@Column(name = "apply_id")
	@Schema(name = "applyId", description = "自增ID")
	private Integer applyId;


	/**
	* @Description: 申请人ID
	*/
	@Column(name = "apply_user_id")
	@Schema(name = "applyUserId", description = "申请人ID")
	private String applyUserId;


	/**
	* @Description: 接收人ID
	*/
	@Column(name = "receive_user_id")
	@Schema(name = "receiveUserId", description = "接收人ID")
	private String receiveUserId;


	/**
	* @Description: 联系人类型   0 好友    1 群组
	*/
	@Column(name = "contact_type")
	@Schema(name = "contactType", description = "联系人类型   0 好友    1 群组")
	private Integer contactType;


	/**
	* @Description: 联系人群组ID
	*/
	@Column(name = "contact_id")
	@Schema(name = "contactId", description = "联系人群组ID")
	private String contactId;


	/**
	* @Description: 最后申请时间
	*/
	@Column(name = "last_apply_time")
	@Schema(name = "lastApplyTime", description = "最后申请时间")
	private Long lastApplyTime;


	/**
	* @Description: 状态： 0 待处理   1 已同意   2 已拒绝  3 已拉黑
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "状态： 0 待处理   1 已同意   2 已拒绝  3 已拉黑")
	private Integer status;


	/**
	* @Description: 申请信息
	*/
	@Column(name = "apply_info")
	@Schema(name = "applyInfo", description = "申请信息")
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