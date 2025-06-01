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
@Table(name = "UserContactApply")
@Schema(name = "UserContactApply", description = "联系人申请")
public class UserContactApply {

	/**
	 * 自增ID
	 */
    @Column(name = "applyId")
    @Schema(description = "自增ID")
    private Integer applyId;



	/**
	 * 申请人ID
	 */
    @Column(name = "applyUserId")
    @Schema(description = "申请人ID")
    private String applyUserId;



	/**
	 * 接收人ID
	 */
    @Column(name = "receiveUserId")
    @Schema(description = "接收人ID")
    private String receiveUserId;



	/**
	 * 联系人类型   0 好友    1 群组
	 */
    @Column(name = "contactType")
    @Schema(description = "联系人类型   0 好友    1 群组")
    private Integer contactType;



	/**
	 * 联系人群组ID
	 */
    @Column(name = "contactId")
    @Schema(description = "联系人群组ID")
    private String contactId;



	/**
	 * 最后申请时间
	 */
    @Column(name = "lastApplyTime")
    @Schema(description = "最后申请时间")
    private Long lastApplyTime;



	/**
	 * 状态： 0 待处理   1 已同意   2 已拒绝  3 已拉黑
	 */
    @Column(name = "status")
    @Schema(description = "状态： 0 待处理   1 已同意   2 已拒绝  3 已拉黑")
    private Integer status;



	/**
	 * 申请信息
	 */
    @Column(name = "applyInfo")
    @Schema(description = "申请信息")
    private String applyInfo;



    public Integer getapplyId() {
        return applyId;

    }

    public void setapplyId(Integer applyId) {
        this.applyId = applyId;

    }



    public String getapplyUserId() {
        return applyUserId;

    }

    public void setapplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;

    }



    public String getreceiveUserId() {
        return receiveUserId;

    }

    public void setreceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;

    }



    public Integer getcontactType() {
        return contactType;

    }

    public void setcontactType(Integer contactType) {
        this.contactType = contactType;

    }



    public String getcontactId() {
        return contactId;

    }

    public void setcontactId(String contactId) {
        this.contactId = contactId;

    }



    public Long getlastApplyTime() {
        return lastApplyTime;

    }

    public void setlastApplyTime(Long lastApplyTime) {
        this.lastApplyTime = lastApplyTime;

    }



    public Integer getstatus() {
        return status;

    }

    public void setstatus(Integer status) {
        this.status = status;

    }



    public String getapplyInfo() {
        return applyInfo;

    }

    public void setapplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;

    }



}