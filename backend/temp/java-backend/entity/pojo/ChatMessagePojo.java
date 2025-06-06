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
@Table(name = "ChatMessage")
@Schema(name = "ChatMessage", description = "聊天消息")
public class ChatMessagePojo  {


	/**
	* @Description: 消息自增id
	*/
	@Column(name = "message_id")
	@Schema(name = "messageId", description = "消息自增id")
	private bigint messageId;


	/**
	* @Description: 会话id
	*/
	@Column(name = "session_id")
	@Schema(name = "sessionId", description = "会话id")
	private varchar sessionId;


	/**
	* @Description: 消息类型
	*/
	@Column(name = "message_type")
	@Schema(name = "messageType", description = "消息类型")
	private tinyint messageType;


	/**
	* @Description: 消息内容
	*/
	@Column(name = "message_content")
	@Schema(name = "messageContent", description = "消息内容")
	private varchar messageContent;


	/**
	* @Description: 发送人id
	*/
	@Column(name = "send_user_id")
	@Schema(name = "sendUserId", description = "发送人id")
	private varchar sendUserId;


	/**
	* @Description: 发送人昵称
	*/
	@Column(name = "send_user_nick_name")
	@Schema(name = "sendUserNickName", description = "发送人昵称")
	private varchar sendUserNickName;


	/**
	* @Description: 发送时间
	*/
	@Column(name = "send_time")
	@Schema(name = "sendTime", description = "发送时间")
	private bigint sendTime;


	/**
	* @Description: 接收联系人id
	*/
	@Column(name = "contact_id")
	@Schema(name = "contactId", description = "接收联系人id")
	private varchar contactId;


	/**
	* @Description: 联系人类型 0：单聊   1：群聊
	*/
	@Column(name = "contact_type")
	@Schema(name = "contactType", description = "联系人类型 0：单聊   1：群聊")
	private tinyint contactType;


	/**
	* @Description: 文件大小
	*/
	@Column(name = "file_size")
	@Schema(name = "fileSize", description = "文件大小")
	private bigint fileSize;


	/**
	* @Description: 文件名称
	*/
	@Column(name = "file_name")
	@Schema(name = "fileName", description = "文件名称")
	private varchar fileName;


	/**
	* @Description: 文件类型
	*/
	@Column(name = "file_type")
	@Schema(name = "fileType", description = "文件类型")
	private tinyint fileType;


	/**
	* @Description: 状态    0：正在发送     1：已发送
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "状态    0：正在发送     1：已发送")
	private tinyint status;


	public ChatMessagePojo() {
	}

	public ChatMessagePojo(bigint messageId, varchar sessionId, tinyint messageType, varchar messageContent, varchar sendUserId, varchar sendUserNickName, bigint sendTime, varchar contactId, tinyint contactType, bigint fileSize, varchar fileName, tinyint fileType, tinyint status) {
		this.messageId = messageId
		this.sessionId = sessionId
		this.messageType = messageType
		this.messageContent = messageContent
		this.sendUserId = sendUserId
		this.sendUserNickName = sendUserNickName
		this.sendTime = sendTime
		this.contactId = contactId
		this.contactType = contactType
		this.fileSize = fileSize
		this.fileName = fileName
		this.fileType = fileType
		this.status = status
	}

	public void setMessageId(bigint messageId) {
		this.messageId = messageId;
	}

	public bigint getMessageId() {
		return this.messageId
	}

	public void setSessionId(varchar sessionId) {
		this.sessionId = sessionId;
	}

	public varchar getSessionId() {
		return this.sessionId
	}

	public void setMessageType(tinyint messageType) {
		this.messageType = messageType;
	}

	public tinyint getMessageType() {
		return this.messageType
	}

	public void setMessageContent(varchar messageContent) {
		this.messageContent = messageContent;
	}

	public varchar getMessageContent() {
		return this.messageContent
	}

	public void setSendUserId(varchar sendUserId) {
		this.sendUserId = sendUserId;
	}

	public varchar getSendUserId() {
		return this.sendUserId
	}

	public void setSendUserNickName(varchar sendUserNickName) {
		this.sendUserNickName = sendUserNickName;
	}

	public varchar getSendUserNickName() {
		return this.sendUserNickName
	}

	public void setSendTime(bigint sendTime) {
		this.sendTime = sendTime;
	}

	public bigint getSendTime() {
		return this.sendTime
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

	public void setFileSize(bigint fileSize) {
		this.fileSize = fileSize;
	}

	public bigint getFileSize() {
		return this.fileSize
	}

	public void setFileName(varchar fileName) {
		this.fileName = fileName;
	}

	public varchar getFileName() {
		return this.fileName
	}

	public void setFileType(tinyint fileType) {
		this.fileType = fileType;
	}

	public tinyint getFileType() {
		return this.fileType
	}

	public void setStatus(tinyint status) {
		this.status = status;
	}

	public tinyint getStatus() {
		return this.status
	}
}