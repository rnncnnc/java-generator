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
@Table(name = "ChatSession")
@Schema(name = "ChatSession", description = "会话信息")
public class ChatSessionPojo  {


	/**
	* @Description: 会话id
	*/
	@Column(name = "session_id")
	@Schema(name = "sessionId", description = "会话id")
	private varchar sessionId;


	/**
	* @Description: 最后接收的消息
	*/
	@Column(name = "last_message")
	@Schema(name = "lastMessage", description = "最后接收的消息")
	private varchar lastMessage;


	/**
	* @Description: 最后接收消息时间毫秒
	*/
	@Column(name = "last_receive_time")
	@Schema(name = "lastReceiveTime", description = "最后接收消息时间毫秒")
	private bigint lastReceiveTime;


	public ChatSessionPojo() {
	}

	public ChatSessionPojo(varchar sessionId, varchar lastMessage, bigint lastReceiveTime) {
		this.sessionId = sessionId
		this.lastMessage = lastMessage
		this.lastReceiveTime = lastReceiveTime
	}

	public void setSessionId(varchar sessionId) {
		this.sessionId = sessionId;
	}

	public varchar getSessionId() {
		return this.sessionId
	}

	public void setLastMessage(varchar lastMessage) {
		this.lastMessage = lastMessage;
	}

	public varchar getLastMessage() {
		return this.lastMessage
	}

	public void setLastReceiveTime(bigint lastReceiveTime) {
		this.lastReceiveTime = lastReceiveTime;
	}

	public bigint getLastReceiveTime() {
		return this.lastReceiveTime
	}
}