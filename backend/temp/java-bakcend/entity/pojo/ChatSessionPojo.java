package com.qinge.bakcend.entity.pojo;

import java.lang.Long;
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
@Table(name = "ChatSession")
@Schema(name = "ChatSession", description = "")
public class ChatSessionPojo  {


	/**
	* @Description: 
	*/
	@Column(name = "session_id")
	@Schema(name = "sessionId", description = "")
	@Id
	private String sessionId;


	/**
	* @Description: 
	*/
	@Column(name = "last_message")
	@Schema(name = "lastMessage", description = "")
	private String lastMessage;


	/**
	* @Description: 
	*/
	@Column(name = "last_receive_time")
	@Schema(name = "lastReceiveTime", description = "")
	private Long lastReceiveTime;


	public ChatSessionPojo() {
	}

	public ChatSessionPojo(String sessionId, String lastMessage, Long lastReceiveTime) {
		this.sessionId = sessionId;
		this.lastMessage = lastMessage;
		this.lastReceiveTime = lastReceiveTime;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}

	public String getLastMessage() {
		return this.lastMessage;
	}

	public void setLastReceiveTime(Long lastReceiveTime) {
		this.lastReceiveTime = lastReceiveTime;
	}

	public Long getLastReceiveTime() {
		return this.lastReceiveTime;
	}
}