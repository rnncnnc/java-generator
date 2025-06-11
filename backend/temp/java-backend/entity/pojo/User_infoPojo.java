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
@Table(name = "User_info")
@Schema(name = "User_info", description = "")
public class User_infoPojo  {


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
	@Column(name = "area_code")
	@Schema(name = "area_code", description = "")
	private String area_code;


	/**
	* @Description: 
	*/
	@Column(name = "area_name")
	@Schema(name = "area_name", description = "")
	private String area_name;


	/**
	* @Description: 
	*/
	@Column(name = "create_time")
	@Schema(name = "create_time", description = "")
	private Long create_time;


	/**
	* @Description: 
	*/
	@Column(name = "email")
	@Schema(name = "email", description = "")
	private String email;


	/**
	* @Description: 
	*/
	@Column(name = "join_type")
	@Schema(name = "join_type", description = "")
	private Integer join_type;


	/**
	* @Description: 
	*/
	@Column(name = "last_login_time")
	@Schema(name = "last_login_time", description = "")
	private Long last_login_time;


	/**
	* @Description: 
	*/
	@Column(name = "last_off_time")
	@Schema(name = "last_off_time", description = "")
	private Long last_off_time;


	/**
	* @Description: 
	*/
	@Column(name = "nick_name")
	@Schema(name = "nick_name", description = "")
	private String nick_name;


	/**
	* @Description: 
	*/
	@Column(name = "password")
	@Schema(name = "password", description = "")
	private String password;


	/**
	* @Description: 
	*/
	@Column(name = "personal_signature")
	@Schema(name = "personal_signature", description = "")
	private String personal_signature;


	/**
	* @Description: 
	*/
	@Column(name = "sex")
	@Schema(name = "sex", description = "")
	private Integer sex;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	public User_infoPojo() {
	}

	public User_infoPojo(String user_id, String area_code, String area_name, Long create_time, String email, Integer join_type, Long last_login_time, Long last_off_time, String nick_name, String password, String personal_signature, Integer sex, Integer status) {
		this.user_id = user_id;
		this.area_code = area_code;
		this.area_name = area_name;
		this.create_time = create_time;
		this.email = email;
		this.join_type = join_type;
		this.last_login_time = last_login_time;
		this.last_off_time = last_off_time;
		this.nick_name = nick_name;
		this.password = password;
		this.personal_signature = personal_signature;
		this.sex = sex;
		this.status = status;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_id() {
		return this.user_id;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getArea_code() {
		return this.area_code;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getArea_name() {
		return this.area_name;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public Long getCreate_time() {
		return this.create_time;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setJoin_type(Integer join_type) {
		this.join_type = join_type;
	}

	public Integer getJoin_type() {
		return this.join_type;
	}

	public void setLast_login_time(Long last_login_time) {
		this.last_login_time = last_login_time;
	}

	public Long getLast_login_time() {
		return this.last_login_time;
	}

	public void setLast_off_time(Long last_off_time) {
		this.last_off_time = last_off_time;
	}

	public Long getLast_off_time() {
		return this.last_off_time;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getNick_name() {
		return this.nick_name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPersonal_signature(String personal_signature) {
		this.personal_signature = personal_signature;
	}

	public String getPersonal_signature() {
		return this.personal_signature;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
}