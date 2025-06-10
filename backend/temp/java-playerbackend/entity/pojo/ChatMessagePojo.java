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
@Table(name = "ChatMessage")
@Schema(name = "ChatMessage", description = "聊天消息")
public class ChatMessagePojo  {


	/**
	* @Description: 消息自增id
	*/
	@Column(name = "message_id")
	@Schema(name = "messageId", description = "消息自增id")
	@Id
	private Long messageId;


	/**
	* @Description: 
	*/
	@Column(name = "session_id")
	@Schema(name = "sessionId", description = "")
	private String sessionId;


	/**
	* @Description: 
	*/
	@Column(name = "message_type")
	@Schema(name = "messageType", description = "")
	private Integer messageType;


	/**
	* @Description: 
	*/
	@Column(name = "message_content")
	@Schema(name = "messageContent", description = "")
	private String messageContent;


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
	* @Description: 发送时间
	*/
	@Column(name = "send_time")
	@Schema(name = "sendTime", description = "发送时间")
	private Long sendTime;


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
	* @Description: 文件大小
	*/
	@Column(name = "file_size")
	@Schema(name = "fileSize", description = "文件大小")
	private Long fileSize;


	/**
	* @Description: 
	*/
	@Column(name = "file_name")
	@Schema(name = "fileName", description = "")
	private String fileName;


	/**
	* @Description: 
	*/
	@Column(name = "file_type")
	@Schema(name = "fileType", description = "")
	private Integer fileType;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	public ChatMessagePojo() {
	}

	public ChatMessagePojo(Long messageId, String sessionId, Integer messageType, String messageContent, String sendUserId, String sendUserNickName, Long sendTime, String contactId, Integer contactType, Long fileSize, String fileName, Integer fileType, Integer status) {
		this.messageId = messageId;
		this.sessionId = sessionId;
		this.messageType = messageType;
		this.messageContent = messageContent;
		this.sendUserId = sendUserId;
		this.sendUserNickName = sendUserNickName;
		this.sendTime = sendTime;
		this.contactId = contactId;
		this.contactType = contactType;
		this.fileSize = fileSize;
		this.fileName = fileName;
		this.fileType = fileType;
		this.status = status;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Long getMessageId() {
		return this.messageId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Integer getMessageType() {
		return this.messageType;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMessageContent() {
		return this.messageContent;
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

	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}

	public Long getSendTime() {
		return this.sendTime;
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

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public Integer getFileType() {
		return this.fileType;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
}