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
@Table(name = "ChatMessage")
@Schema(name = "ChatMessage", description = "")
public class ChatMessagePojo  {


	/**
	* @Description: 
	*/
	@Column(name = "message_id")
	@Schema(name = "messageId", description = "")
	@Id
	private Long messageId;


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
	@Column(name = "file_name")
	@Schema(name = "fileName", description = "")
	private String fileName;


	/**
	* @Description: 
	*/
	@Column(name = "file_size")
	@Schema(name = "fileSize", description = "")
	private Long fileSize;


	/**
	* @Description: 
	*/
	@Column(name = "file_type")
	@Schema(name = "fileType", description = "")
	private Integer fileType;


	/**
	* @Description: 
	*/
	@Column(name = "message_content")
	@Schema(name = "messageContent", description = "")
	private String messageContent;


	/**
	* @Description: 
	*/
	@Column(name = "message_type")
	@Schema(name = "messageType", description = "")
	private Integer messageType;


	/**
	* @Description: 
	*/
	@Column(name = "send_time")
	@Schema(name = "sendTime", description = "")
	private Long sendTime;


	/**
	* @Description: 
	*/
	@Column(name = "send_user_id")
	@Schema(name = "sendUserId", description = "")
	private String sendUserId;


	/**
	* @Description: 
	*/
	@Column(name = "send_user_nick_name")
	@Schema(name = "sendUserNickName", description = "")
	private String sendUserNickName;


	/**
	* @Description: 
	*/
	@Column(name = "session_id")
	@Schema(name = "sessionId", description = "")
	private String sessionId;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	public ChatMessagePojo() {
	}

	public ChatMessagePojo(Long messageId, String contactId, Integer contactType, String fileName, Long fileSize, Integer fileType, String messageContent, Integer messageType, Long sendTime, String sendUserId, String sendUserNickName, String sessionId, Integer status) {
		this.messageId = messageId;
		this.contactId = contactId;
		this.contactType = contactType;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.messageContent = messageContent;
		this.messageType = messageType;
		this.sendTime = sendTime;
		this.sendUserId = sendUserId;
		this.sendUserNickName = sendUserNickName;
		this.sessionId = sessionId;
		this.status = status;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getMessageId() {
		return this.messageId;
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

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public Integer getFileType() {
		return this.fileType;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMessageContent() {
		return this.messageContent;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Integer getMessageType() {
		return this.messageType;
	}

	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}

	public Long getSendTime() {
		return this.sendTime;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getSendUserId() {
		return this.sendUserId;
	}

	public void setSendUserNickName(String sendUserNickName) {
		this.sendUserNickName = sendUserNickName;
	}

	public String getSendUserNickName() {
		return this.sendUserNickName;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
}