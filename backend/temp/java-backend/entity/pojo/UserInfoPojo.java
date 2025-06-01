package com.qinge.backend.entity.pojo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.Long;
import java.lang.String;
import java.lang.Integer;
import jakarta.persistence.Table;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */


@Entity
@Table(name = "UserInfo")
@Schema(name = "UserInfo", description = "用户信息表")
public class UserInfo {

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
	 * 昵称
	 */
    @Column(name = "nickName")
    @Schema(description = "昵称")
    private String nickName;



	/**
	 * 0：直接加入   1：同意后加好友
	 */
    @Column(name = "joinType")
    @Schema(description = "0：直接加入   1：同意后加好友")
    private Integer joinType;



	/**
	 * 性别 0：女  1：男
	 */
    @Column(name = "sex")
    @Schema(description = "性别 0：女  1：男")
    private Integer sex;



	/**
	 * 密码
	 */
    @Column(name = "password")
    @Schema(description = "密码")
    private String password;



	/**
	 * 个性签名
	 */
    @Column(name = "personalSignature")
    @Schema(description = "个性签名")
    private String personalSignature;



	/**
	 * 状态
	 */
    @Column(name = "status")
    @Schema(description = "状态")
    private Integer status;



	/**
	 * 创建时间
	 */
    @Column(name = "createTime")
    @Schema(description = "创建时间")
    private Long createTime;



	/**
	 * 最后登录时间
	 */
    @Column(name = "lastLoginTime")
    @Schema(description = "最后登录时间")
    private Long lastLoginTime;



	/**
	 * 最后离开时间
	 */
    @Column(name = "lastOffTime")
    @Schema(description = "最后离开时间")
    private Long lastOffTime;



	/**
	 * 地区
	 */
    @Column(name = "areaName")
    @Schema(description = "地区")
    private String areaName;



	/**
	 * 地区编号
	 */
    @Column(name = "areaCode")
    @Schema(description = "地区编号")
    private String areaCode;



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



    public String getnickName() {
        return nickName;

    }

    public void setnickName(String nickName) {
        this.nickName = nickName;

    }



    public Integer getjoinType() {
        return joinType;

    }

    public void setjoinType(Integer joinType) {
        this.joinType = joinType;

    }



    public Integer getsex() {
        return sex;

    }

    public void setsex(Integer sex) {
        this.sex = sex;

    }



    public String getpassword() {
        return password;

    }

    public void setpassword(String password) {
        this.password = password;

    }



    public String getpersonalSignature() {
        return personalSignature;

    }

    public void setpersonalSignature(String personalSignature) {
        this.personalSignature = personalSignature;

    }



    public Integer getstatus() {
        return status;

    }

    public void setstatus(Integer status) {
        this.status = status;

    }



    public Long getcreateTime() {
        return createTime;

    }

    public void setcreateTime(Long createTime) {
        this.createTime = createTime;

    }



    public Long getlastLoginTime() {
        return lastLoginTime;

    }

    public void setlastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;

    }



    public Long getlastOffTime() {
        return lastOffTime;

    }

    public void setlastOffTime(Long lastOffTime) {
        this.lastOffTime = lastOffTime;

    }



    public String getareaName() {
        return areaName;

    }

    public void setareaName(String areaName) {
        this.areaName = areaName;

    }



    public String getareaCode() {
        return areaCode;

    }

    public void setareaCode(String areaCode) {
        this.areaCode = areaCode;

    }



}