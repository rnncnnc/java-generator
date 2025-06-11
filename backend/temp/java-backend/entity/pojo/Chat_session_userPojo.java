package com.qinge.backend.entity.pojo;

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
@Table(name = "Chat_session_user")
@Schema(name = "Chat_session_user", description = "")
public class Chat_session_userPojo  {


	/**
	* @Description: 
	*/
	@Column(name = "user_id")
	@Schema(name = "user_id", description = "")
	@Id
	private String user_id;


	/**
	* @Description: 
	*/
	@Column(name = "contact_id")
	@Schema(name = "contact_id", description = "")
	@Id
	private String contact_id;


	/**
	* @Description: 
	*/
	@Column(name = "contact_name")
	@Schema(name = "contact_name", description = "")
	private String contact_name;


	/**
	* @Description: 
	*/
	@Column(name = "session_id")
	@Schema(name = "session_id", description = "")
	private String session_id;


	public Chat_session_userPojo() {
	}

	public Chat_session_userPojo(String user_id, String contact_id, String contact_name, String session_id) {
		this.user_id = user_id;
		this.contact_id = contact_id;
		this.contact_name = contact_name;
		this.session_id = session_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_id() {
		return this.user_id;
	}

	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}

	public String getContact_id() {
		return this.contact_id;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public String getContact_name() {
		return this.contact_name;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getSession_id() {
		return this.session_id;
	}
}