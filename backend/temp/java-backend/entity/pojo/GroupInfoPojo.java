package com.qinge.backend.entity.pojo;

import java.lang.Long;
import java.lang.Integer;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;


/**
* @Data: 2025/06/10 16:08
 * @Author: Lc
 * @Description:
 */


@Entity
@Table(name = "GroupInfo")
@Schema(name = "GroupInfo", description = "群组表")
public class GroupInfoPojo  {


	/**
	* @Description: 群ID
	*/
	@Column(name = "group_id")
	@Schema(name = "groupId", description = "群ID")
	private String groupId;


	/**
	* @Description: 群组名
	*/
	@Column(name = "group_name")
	@Schema(name = "groupName", description = "群组名")
	private String groupName;


	/**
	* @Description: 群主id
	*/
	@Column(name = "group_owner_id")
	@Schema(name = "groupOwnerId", description = "群主id")
	private String groupOwnerId;


	/**
	* @Description: 创建时间
	*/
	@Column(name = "create_time")
	@Schema(name = "createTime", description = "创建时间")
	private Long createTime;


	/**
	* @Description: 群公告
	*/
	@Column(name = "group_notice")
	@Schema(name = "groupNotice", description = "群公告")
	private String groupNotice;


	/**
	* @Description: 0：直接加入    1：管理员同意后加入
	*/
	@Column(name = "join_type")
	@Schema(name = "joinType", description = "0：直接加入    1：管理员同意后加入")
	private Integer joinType;


	/**
	* @Description: 状态  1：正常    0：解散
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "状态  1：正常    0：解散")
	private Integer status;


	public GroupInfoPojo() {
	}

	public GroupInfoPojo(String groupId, String groupName, String groupOwnerId, Long createTime, String groupNotice, Integer joinType, Integer status) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupOwnerId = groupOwnerId;
		this.createTime = createTime;
		this.groupNotice = groupNotice;
		this.joinType = joinType;
		this.status = status;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupOwnerId(String groupOwnerId) {
		this.groupOwnerId = groupOwnerId;
	}

	public String getGroupOwnerId() {
		return this.groupOwnerId;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setGroupNotice(String groupNotice) {
		this.groupNotice = groupNotice;
	}

	public String getGroupNotice() {
		return this.groupNotice;
	}

	public void setJoinType(Integer joinType) {
		this.joinType = joinType;
	}

	public Integer getJoinType() {
		return this.joinType;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
}