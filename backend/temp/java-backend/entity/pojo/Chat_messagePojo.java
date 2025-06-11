package com.qinge.backend.entity.pojo;

import java.lang.Long;
import java.lang.Integer;
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
@Table(name = "Chat_message")
@Schema(name = "Chat_message", description = "")
public class Chat_messagePojo  {


	/**
	* @Description: 
	*/
	@Column(name = "message_id")
	@Schema(name = "message_id", description = "")
	@Id
	private Long message_id;


	/**
	* @Description: 
	*/
	@Column(name = "contact_id")
	@Schema(name = "contact_id", description = "")
	private String contact_id;


	/**
	* @Description: 
	*/
	@Column(name = "contact_type")
	@Schema(name = "contact_type", description = "")
	private Integer contact_type;


	/**
	* @Description: 
	*/
	@Column(name = "file_name")
	@Schema(name = "file_name", description = "")
	private String file_name;


	/**
	* @Description: 
	*/
	@Column(name = "file_size")
	@Schema(name = "file_size", description = "")
	private Long file_size;


	/**
	* @Description: 
	*/
	@Column(name = "file_type")
	@Schema(name = "file_type", description = "")
	private Integer file_type;


	/**
	* @Description: 
	*/
	@Column(name = "message_content")
	@Schema(name = "message_content", description = "")
	private String message_content;


	/**
	* @Description: 
	*/
	@Column(name = "message_type")
	@Schema(name = "message_type", description = "")
	private Integer message_type;


	/**
	* @Description: 
	*/
	@Column(name = "send_time")
	@Schema(name = "send_time", description = "")
	private Long send_time;


	/**
	* @Description: 
	*/
	@Column(name = "send_user_id")
	@Schema(name = "send_user_id", description = "")
	private String send_user_id;


	/**
	* @Description: 
	*/
	@Column(name = "send_user_nick_name")
	@Schema(name = "send_user_nick_name", description = "")
	private String send_user_nick_name;


	/**
	* @Description: 
	*/
	@Column(name = "session_id")
	@Schema(name = "session_id", description = "")
	private String session_id;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	public Chat_messagePojo() {
	}

	public Chat_messagePojo(Long message_id, String contact_id, Integer contact_type, String file_name, Long file_size, Integer file_type, String message_content, Integer message_type, Long send_time, String send_user_id, String send_user_nick_name, String session_id, Integer status) {
		this.message_id = message_id;
		this.contact_id = contact_id;
		this.contact_type = contact_type;
		this.file_name = file_name;
		this.file_size = file_size;
		this.file_type = file_type;
		this.message_content = message_content;
		this.message_type = message_type;
		this.send_time = send_time;
		this.send_user_id = send_user_id;
		this.send_user_nick_name = send_user_nick_name;
		this.session_id = session_id;
		this.status = status;
	}

	public void setMessage_id(Long message_id) {
		this.message_id = message_id;
	}

	public Long getMessage_id() {
		return this.message_id;
	}

	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}

	public String getContact_id() {
		return this.contact_id;
	}

	public void setContact_type(Integer contact_type) {
		this.contact_type = contact_type;
	}

	public Integer getContact_type() {
		return this.contact_type;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_name() {
		return this.file_name;
	}

	public void setFile_size(Long file_size) {
		this.file_size = file_size;
	}

	public Long getFile_size() {
		return this.file_size;
	}

	public void setFile_type(Integer file_type) {
		this.file_type = file_type;
	}

	public Integer getFile_type() {
		return this.file_type;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public String getMessage_content() {
		return this.message_content;
	}

	public void setMessage_type(Integer message_type) {
		this.message_type = message_type;
	}

	public Integer getMessage_type() {
		return this.message_type;
	}

	public void setSend_time(Long send_time) {
		this.send_time = send_time;
	}

	public Long getSend_time() {
		return this.send_time;
	}

	public void setSend_user_id(String send_user_id) {
		this.send_user_id = send_user_id;
	}

	public String getSend_user_id() {
		return this.send_user_id;
	}

	public void setSend_user_nick_name(String send_user_nick_name) {
		this.send_user_nick_name = send_user_nick_name;
	}

	public String getSend_user_nick_name() {
		return this.send_user_nick_name;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getSession_id() {
		return this.session_id;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
}