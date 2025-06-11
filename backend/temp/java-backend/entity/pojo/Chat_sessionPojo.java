package com.qinge.backend.entity.pojo;

import java.lang.Long;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;
import jakarta.persistence.Id;


/**
* @Data: 2025/06/11 20:57
 * @Author: Lc
 * @Description:
 */


@Entity
@Table(name = "Chat_session")
@Schema(name = "Chat_session", description = "")
public class Chat_sessionPojo  {


	/**
	* @Description: 
	*/
	@Column(name = "session_id")
	@Schema(name = "session_id", description = "")
	@Id
	private String session_id;


	/**
	* @Description: 
	*/
	@Column(name = "last_message")
	@Schema(name = "last_message", description = "")
	private String last_message;


	/**
	* @Description: 
	*/
	@Column(name = "last_receive_time")
	@Schema(name = "last_receive_time", description = "")
	private Long last_receive_time;


	public Chat_sessionPojo() {
	}

	public Chat_sessionPojo(String session_id, String last_message, Long last_receive_time) {
		this.session_id = session_id;
		this.last_message = last_message;
		this.last_receive_time = last_receive_time;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getSession_id() {
		return this.session_id;
	}

	public void setLast_message(String last_message) {
		this.last_message = last_message;
	}

	public String getLast_message() {
		return this.last_message;
	}

	public void setLast_receive_time(Long last_receive_time) {
		this.last_receive_time = last_receive_time;
	}

	public Long getLast_receive_time() {
		return this.last_receive_time;
	}
}