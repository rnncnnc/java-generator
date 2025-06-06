package com.qinge.backend.entity.pojo;

import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;


/**
* @Data: 2025/06/07 01:59
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
	private varchar groupId;


	/**
	* @Description: 群组名
	*/
	@Column(name = "group_name")
	@Schema(name = "groupName", description = "群组名")
	private varchar groupName;


	/**
	* @Description: 群主id
	*/
	@Column(name = "group_owner_id")
	@Schema(name = "groupOwnerId", description = "群主id")
	private varchar groupOwnerId;


	/**
	* @Description: 创建时间
	*/
	@Column(name = "create_time")
	@Schema(name = "createTime", description = "创建时间")
	private datetime createTime;


	/**
	* @Description: 群公告
	*/
	@Column(name = "group_notice")
	@Schema(name = "groupNotice", description = "群公告")
	private varchar groupNotice;


	/**
	* @Description: 0：直接加入    1：管理员同意后加入
	*/
	@Column(name = "join_type")
	@Schema(name = "joinType", description = "0：直接加入    1：管理员同意后加入")
	private tinyint joinType;


	/**
	* @Description: 状态  1：正常    0：解散
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "状态  1：正常    0：解散")
	private tinyint status;


	public GroupInfoPojo() {
	}

	public GroupInfoPojo(varchar groupId, varchar groupName, varchar groupOwnerId, datetime createTime, varchar groupNotice, tinyint joinType, tinyint status) {
		this.groupId = groupId
		this.groupName = groupName
		this.groupOwnerId = groupOwnerId
		this.createTime = createTime
		this.groupNotice = groupNotice
		this.joinType = joinType
		this.status = status
	}

	public void setGroupId(varchar groupId) {
		this.groupId = groupId;
	}

	public varchar getGroupId() {
		return this.groupId
	}

	public void setGroupName(varchar groupName) {
		this.groupName = groupName;
	}

	public varchar getGroupName() {
		return this.groupName
	}

	public void setGroupOwnerId(varchar groupOwnerId) {
		this.groupOwnerId = groupOwnerId;
	}

	public varchar getGroupOwnerId() {
		return this.groupOwnerId
	}

	public void setCreateTime(datetime createTime) {
		this.createTime = createTime;
	}

	public datetime getCreateTime() {
		return this.createTime
	}

	public void setGroupNotice(varchar groupNotice) {
		this.groupNotice = groupNotice;
	}

	public varchar getGroupNotice() {
		return this.groupNotice
	}

	public void setJoinType(tinyint joinType) {
		this.joinType = joinType;
	}

	public tinyint getJoinType() {
		return this.joinType
	}

	public void setStatus(tinyint status) {
		this.status = status;
	}

	public tinyint getStatus() {
		return this.status
	}
}