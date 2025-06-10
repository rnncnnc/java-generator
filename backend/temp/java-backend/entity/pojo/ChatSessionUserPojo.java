package com.qinge.backend.entity.pojo;

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
@Table(name = "ChatSessionUser")
@Schema(name = "ChatSessionUser", description = "")
public class ChatSessionUserPojo  {


	/**
	* @Description: 用户id
	*/
	@Column(name = "user_id")
	@Schema(name = "userId", description = "用户id")
	private String userId;


	/**
	* @Description: 联系人id
	*/
	@Column(name = "contact_id")
	@Schema(name = "contactId", description = "联系人id")
	private String contactId;


	/**
	* @Description: 会话id
	*/
	@Column(name = "session_id")
	@Schema(name = "sessionId", description = "会话id")
	private String sessionId;


	/**
	* @Description: 联系人名称
	*/
	@Column(name = "contact_name")
	@Schema(name = "contactName", description = "联系人名称")
	private String contactName;


	public ChatSessionUserPojo() {
	}

	public ChatSessionUserPojo(String userId, String contactId, String sessionId, String contactName) {
		this.userId = userId;
		this.contactId = contactId;
		this.sessionId = sessionId;
		this.contactName = contactName;
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

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactName() {
		return this.contactName;
	}
}