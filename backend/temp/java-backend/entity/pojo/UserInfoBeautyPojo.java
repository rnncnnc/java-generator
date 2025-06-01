package com.qinge.backend.entity.pojo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;
import java.lang.Integer;
import jakarta.persistence.Table;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */


@Entity
@Table(name = "UserInfoBeauty")
@Schema(name = "UserInfoBeauty", description = "靓号表")
public class UserInfoBeauty {

	/**
	 * 自增id
	 */
    @Column(name = "id")
    @Schema(description = "自增id")
    private Integer id;



	/**
	 * 邮箱
	 */
    @Column(name = "email")
    @Schema(description = "邮箱")
    private String email;



	/**
	 * 用户id
	 */
    @Column(name = "userId")
    @Schema(description = "用户id")
    private String userId;



	/**
	 * 0：未使用   1：已使用
	 */
    @Column(name = "status")
    @Schema(description = "0：未使用   1：已使用")
    private Integer status;



    public Integer getid() {
        return id;

    }

    public void setid(Integer id) {
        this.id = id;

    }



    public String getemail() {
        return email;

    }

    public void setemail(String email) {
        this.email = email;

    }



    public String getuserId() {
        return userId;

    }

    public void setuserId(String userId) {
        this.userId = userId;

    }



    public Integer getstatus() {
        return status;

    }

    public void setstatus(Integer status) {
        this.status = status;

    }



}