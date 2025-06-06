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
@Table(name = "ChatSessionUser")
@Schema(name = "ChatSessionUser", description = "")
public class ChatSessionUserPojo  {


	/**
	* @Description: 用户id
	*/
	@Column(name = "user_id")
	@Schema(name = "userId", description = "用户id")
	private varchar userId;


	/**
	* @Description: 联系人id
	*/
	@Column(name = "contact_id")
	@Schema(name = "contactId", description = "联系人id")
	private varchar contactId;


	/**
	* @Description: 会话id
	*/
	@Column(name = "session_id")
	@Schema(name = "sessionId", description = "会话id")
	private varchar sessionId;


	/**
	* @Description: 联系人名称
	*/
	@Column(name = "contact_name")
	@Schema(name = "contactName", description = "联系人名称")
	private varchar contactName;


	public ChatSessionUserPojo() {
	}

	public ChatSessionUserPojo(varchar userId, varchar contactId, varchar sessionId, varchar contactName) {
		this.userId = userId
		this.contactId = contactId
		this.sessionId = sessionId
		this.contactName = contactName
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

	public void setSessionId(varchar sessionId) {
		this.sessionId = sessionId;
	}

	public varchar getSessionId() {
		return this.sessionId
	}

	public void setContactName(varchar contactName) {
		this.contactName = contactName;
	}

	public varchar getContactName() {
		return this.contactName
	}
}