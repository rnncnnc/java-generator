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
@Table(name = "AppUpdate")
@Schema(name = "AppUpdate", description = "")
public class AppUpdatePojo  {


	/**
	* @Description: 自增id
	*/
	@Column(name = "id")
	@Schema(name = "id", description = "自增id")
	private int id;


	/**
	* @Description: 版本号
	*/
	@Column(name = "version")
	@Schema(name = "version", description = "版本号")
	private varchar version;


	/**
	* @Description: 更新描述
	*/
	@Column(name = "update_desc")
	@Schema(name = "updateDesc", description = "更新描述")
	private varchar updateDesc;


	/**
	* @Description: 0：未发布 1：灰度发布 2：全网发布
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "0：未发布 1：灰度发布 2：全网发布")
	private tinyint status;


	/**
	* @Description: 创建时间
	*/
	@Column(name = "create_time")
	@Schema(name = "createTime", description = "创建时间")
	private datetime createTime;


	/**
	* @Description: 灰度uid
	*/
	@Column(name = "grayscale_uid")
	@Schema(name = "grayscaleUid", description = "灰度uid")
	private varchar grayscaleUid;


	/**
	* @Description: 文件类型 0：本地文件 1：外链
	*/
	@Column(name = "file_type")
	@Schema(name = "fileType", description = "文件类型 0：本地文件 1：外链")
	private tinyint fileType;


	/**
	* @Description: 外链地址
	*/
	@Column(name = "outer_link")
	@Schema(name = "outerLink", description = "外链地址")
	private varchar outerLink;


	public AppUpdatePojo() {
	}

	public AppUpdatePojo(int id, varchar version, varchar updateDesc, tinyint status, datetime createTime, varchar grayscaleUid, tinyint fileType, varchar outerLink) {
		this.id = id
		this.version = version
		this.updateDesc = updateDesc
		this.status = status
		this.createTime = createTime
		this.grayscaleUid = grayscaleUid
		this.fileType = fileType
		this.outerLink = outerLink
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id
	}

	public void setVersion(varchar version) {
		this.version = version;
	}

	public varchar getVersion() {
		return this.version
	}

	public void setUpdateDesc(varchar updateDesc) {
		this.updateDesc = updateDesc;
	}

	public varchar getUpdateDesc() {
		return this.updateDesc
	}

	public void setStatus(tinyint status) {
		this.status = status;
	}

	public tinyint getStatus() {
		return this.status
	}

	public void setCreateTime(datetime createTime) {
		this.createTime = createTime;
	}

	public datetime getCreateTime() {
		return this.createTime
	}

	public void setGrayscaleUid(varchar grayscaleUid) {
		this.grayscaleUid = grayscaleUid;
	}

	public varchar getGrayscaleUid() {
		return this.grayscaleUid
	}

	public void setFileType(tinyint fileType) {
		this.fileType = fileType;
	}

	public tinyint getFileType() {
		return this.fileType
	}

	public void setOuterLink(varchar outerLink) {
		this.outerLink = outerLink;
	}

	public varchar getOuterLink() {
		return this.outerLink
	}
}