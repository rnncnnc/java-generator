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
@Table(name = "UserContact")
@Schema(name = "UserContact", description = "联系人")
public class UserContact {

	/**
	 * 用户ID
	 */
    @Column(name = "userId")
    @Schema(description = "用户ID")
    private String userId;



	/**
	 * 联系人ID或群组ID
	 */
    @Column(name = "contactId")
    @Schema(description = "联系人ID或群组ID")
    private String contactId;



	/**
	 * 联系人类型：0 好友    1 群组
	 */
    @Column(name = "contactType")
    @Schema(description = "联系人类型：0 好友    1 群组")
    private Integer contactType;



	/**
	 * 创建时间
	 */
    @Column(name = "createTime")
    @Schema(description = "创建时间")
    private Long createTime;



	/**
	 * 状态： 0 非好友  1 好友  2 已删除好友   3 被好友删除   4 已拉黑好友    5 被好友拉黑
	 */
    @Column(name = "status")
    @Schema(description = "状态： 0 非好友  1 好友  2 已删除好友   3 被好友删除   4 已拉黑好友    5 被好友拉黑")
    private Integer status;



	/**
	 * 最后更新时间
	 */
    @Column(name = "lastUpdateTime")
    @Schema(description = "最后更新时间")
    private Long lastUpdateTime;



    public String getuserId() {
        return userId;

    }

    public void setuserId(String userId) {
        this.userId = userId;

    }



    public String getcontactId() {
        return contactId;

    }

    public void setcontactId(String contactId) {
        this.contactId = contactId;

    }



    public Integer getcontactType() {
        return contactType;

    }

    public void setcontactType(Integer contactType) {
        this.contactType = contactType;

    }



    public Long getcreateTime() {
        return createTime;

    }

    public void setcreateTime(Long createTime) {
        this.createTime = createTime;

    }



    public Integer getstatus() {
        return status;

    }

    public void setstatus(Integer status) {
        this.status = status;

    }



    public Long getlastUpdateTime() {
        return lastUpdateTime;

    }

    public void setlastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;

    }



}