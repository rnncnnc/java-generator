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
@Table(name = "GroupInfo")
@Schema(name = "GroupInfo", description = "群组表
")
public class GroupInfo {

	/**
	 * 群ID
 
	 */
    @Column(name = "groupId")
    @Schema(description = "群ID
 ")
    private String groupId;



	/**
	 * 群组名
	 */
    @Column(name = "groupName")
    @Schema(description = "群组名")
    private String groupName;



	/**
	 * 群主id
	 */
    @Column(name = "groupOwnerId")
    @Schema(description = "群主id")
    private String groupOwnerId;



	/**
	 * 创建时间
	 */
    @Column(name = "createTime")
    @Schema(description = "创建时间")
    private Long createTime;



	/**
	 * 群公告
	 */
    @Column(name = "groupNotice")
    @Schema(description = "群公告")
    private String groupNotice;



	/**
	 * 0：直接加入    1：管理员同意后加入
	 */
    @Column(name = "joinType")
    @Schema(description = "0：直接加入    1：管理员同意后加入")
    private Integer joinType;



	/**
	 * 状态  1：正常    0：解散
	 */
    @Column(name = "status")
    @Schema(description = "状态  1：正常    0：解散")
    private Integer status;



    public String getgroupId() {
        return groupId;

    }

    public void setgroupId(String groupId) {
        this.groupId = groupId;

    }



    public String getgroupName() {
        return groupName;

    }

    public void setgroupName(String groupName) {
        this.groupName = groupName;

    }



    public String getgroupOwnerId() {
        return groupOwnerId;

    }

    public void setgroupOwnerId(String groupOwnerId) {
        this.groupOwnerId = groupOwnerId;

    }



    public Long getcreateTime() {
        return createTime;

    }

    public void setcreateTime(Long createTime) {
        this.createTime = createTime;

    }



    public String getgroupNotice() {
        return groupNotice;

    }

    public void setgroupNotice(String groupNotice) {
        this.groupNotice = groupNotice;

    }



    public Integer getjoinType() {
        return joinType;

    }

    public void setjoinType(Integer joinType) {
        this.joinType = joinType;

    }



    public Integer getstatus() {
        return status;

    }

    public void setstatus(Integer status) {
        this.status = status;

    }



}