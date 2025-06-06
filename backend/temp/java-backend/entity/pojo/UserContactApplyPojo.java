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
@Table(name = "UserContactApply")
@Schema(name = "UserContactApply", description = "联系人申请")
public class UserContactApplyPojo  {


	/**
	* @Description: 自增ID
	*/
	@Column(name = "apply_id")
	@Schema(name = "applyId", description = "自增ID")
	private int applyId;


	/**
	* @Description: 申请人ID
	*/
	@Column(name = "apply_user_id")
	@Schema(name = "applyUserId", description = "申请人ID")
	private varchar applyUserId;


	/**
	* @Description: 接收人ID
	*/
	@Column(name = "receive_user_id")
	@Schema(name = "receiveUserId", description = "接收人ID")
	private varchar receiveUserId;


	/**
	* @Description: 联系人类型   0 好友    1 群组
	*/
	@Column(name = "contact_type")
	@Schema(name = "contactType", description = "联系人类型   0 好友    1 群组")
	private tinyint contactType;


	/**
	* @Description: 联系人群组ID
	*/
	@Column(name = "contact_id")
	@Schema(name = "contactId", description = "联系人群组ID")
	private varchar contactId;


	/**
	* @Description: 最后申请时间
	*/
	@Column(name = "last_apply_time")
	@Schema(name = "lastApplyTime", description = "最后申请时间")
	private bigint lastApplyTime;


	/**
	* @Description: 状态： 0 待处理   1 已同意   2 已拒绝  3 已拉黑
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "状态： 0 待处理   1 已同意   2 已拒绝  3 已拉黑")
	private tinyint status;


	/**
	* @Description: 申请信息
	*/
	@Column(name = "apply_info")
	@Schema(name = "applyInfo", description = "申请信息")
	private varchar applyInfo;


	public UserContactApplyPojo() {
	}

	public UserContactApplyPojo(int applyId, varchar applyUserId, varchar receiveUserId, tinyint contactType, varchar contactId, bigint lastApplyTime, tinyint status, varchar applyInfo) {
		this.applyId = applyId
		this.applyUserId = applyUserId
		this.receiveUserId = receiveUserId
		this.contactType = contactType
		this.contactId = contactId
		this.lastApplyTime = lastApplyTime
		this.status = status
		this.applyInfo = applyInfo
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}

	public int getApplyId() {
		return this.applyId
	}

	public void setApplyUserId(varchar applyUserId) {
		this.applyUserId = applyUserId;
	}

	public varchar getApplyUserId() {
		return this.applyUserId
	}

	public void setReceiveUserId(varchar receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public varchar getReceiveUserId() {
		return this.receiveUserId
	}

	public void setContactType(tinyint contactType) {
		this.contactType = contactType;
	}

	public tinyint getContactType() {
		return this.contactType
	}

	public void setContactId(varchar contactId) {
		this.contactId = contactId;
	}

	public varchar getContactId() {
		return this.contactId
	}

	public void setLastApplyTime(bigint lastApplyTime) {
		this.lastApplyTime = lastApplyTime;
	}

	public bigint getLastApplyTime() {
		return this.lastApplyTime
	}

	public void setStatus(tinyint status) {
		this.status = status;
	}

	public tinyint getStatus() {
		return this.status
	}

	public void setApplyInfo(varchar applyInfo) {
		this.applyInfo = applyInfo;
	}

	public varchar getApplyInfo() {
		return this.applyInfo
	}
}