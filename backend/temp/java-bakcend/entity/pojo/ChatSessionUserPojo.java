package com.qinge.bakcend.entity.pojo;

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
@Table(name = "ChatSessionUser")
@Schema(name = "ChatSessionUser", description = "")
public class ChatSessionUserPojo  {


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
	@Column(name = "contact_name")
	@Schema(name = "contactName", description = "")
	private String contactName;


	/**
	* @Description: 
	*/
	@Column(name = "session_id")
	@Schema(name = "sessionId", description = "")
	private String sessionId;


	public ChatSessionUserPojo() {
	}

	public ChatSessionUserPojo(String userId, String contactId, String contactName, String sessionId) {
		this.userId = userId;
		this.contactId = contactId;
		this.contactName = contactName;
		this.sessionId = sessionId;
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

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactName() {
		return this.contactName;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return this.sessionId;
	}
}