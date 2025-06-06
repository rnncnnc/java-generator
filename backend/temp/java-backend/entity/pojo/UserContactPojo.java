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
@Table(name = "UserContact")
@Schema(name = "UserContact", description = "联系人")
public class UserContactPojo  {


	/**
	* @Description: 用户ID
	*/
	@Column(name = "user_id")
	@Schema(name = "userId", description = "用户ID")
	private varchar userId;


	/**
	* @Description: 联系人ID或群组ID
	*/
	@Column(name = "contact_id")
	@Schema(name = "contactId", description = "联系人ID或群组ID")
	private varchar contactId;


	/**
	* @Description: 联系人类型：0 好友    1 群组
	*/
	@Column(name = "contact_type")
	@Schema(name = "contactType", description = "联系人类型：0 好友    1 群组")
	private tinyint contactType;


	/**
	* @Description: 创建时间
	*/
	@Column(name = "create_time")
	@Schema(name = "createTime", description = "创建时间")
	private datetime createTime;


	/**
	* @Description: 状态： 0 非好友  1 好友  2 已删除好友   3 被好友删除   4 已拉黑好友    5 被好友拉黑
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "状态： 0 非好友  1 好友  2 已删除好友   3 被好友删除   4 已拉黑好友    5 被好友拉黑")
	private tinyint status;


	/**
	* @Description: 最后更新时间
	*/
	@Column(name = "last_update_time")
	@Schema(name = "lastUpdateTime", description = "最后更新时间")
	private datetime lastUpdateTime;


	public UserContactPojo() {
	}

	public UserContactPojo(varchar userId, varchar contactId, tinyint contactType, datetime createTime, tinyint status, datetime lastUpdateTime) {
		this.userId = userId
		this.contactId = contactId
		this.contactType = contactType
		this.createTime = createTime
		this.status = status
		this.lastUpdateTime = lastUpdateTime
	}

	public void setUserId(varchar userId) {
		this.userId = userId;
	}

	public varchar getUserId() {
		return this.userId
	}

	public void setContactId(varchar contactId) {
		this.contactId = contactId;
	}

	public varchar getContactId() {
		return this.contactId
	}

	public void setContactType(tinyint contactType) {
		this.contactType = contactType;
	}

	public tinyint getContactType() {
		return this.contactType
	}

	public void setCreateTime(datetime createTime) {
		this.createTime = createTime;
	}

	public datetime getCreateTime() {
		return this.createTime
	}

	public void setStatus(tinyint status) {
		this.status = status;
	}

	public tinyint getStatus() {
		return this.status
	}

	public void setLastUpdateTime(datetime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public datetime getLastUpdateTime() {
		return this.lastUpdateTime
	}
}