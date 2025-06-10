package com.qinge.playerbackend.entity.pojo;

import java.lang.Long;
import java.lang.Integer;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;
import jakarta.persistence.Id;


/**
* @Data: 2025/06/10 18:54
 * @Author: Lc
 * @Description:
 */


@Entity
@Table(name = "GroupInfo")
@Schema(name = "GroupInfo", description = "群组表")
public class GroupInfoPojo  {


	/**
	* @Description: 
	*/
	@Column(name = "group_id")
	@Schema(name = "groupId", description = "")
	@Id
	private String groupId;


	/**
	* @Description: 
	*/
	@Column(name = "group_name")
	@Schema(name = "groupName", description = "")
	private String groupName;


	/**
	* @Description: 
	*/
	@Column(name = "group_owner_id")
	@Schema(name = "groupOwnerId", description = "")
	private String groupOwnerId;


	/**
	* @Description: 
	*/
	@Column(name = "create_time")
	@Schema(name = "createTime", description = "")
	private Long createTime;


	/**
	* @Description: 
	*/
	@Column(name = "group_notice")
	@Schema(name = "groupNotice", description = "")
	private String groupNotice;


	/**
	* @Description: 
	*/
	@Column(name = "join_type")
	@Schema(name = "joinType", description = "")
	private Integer joinType;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
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