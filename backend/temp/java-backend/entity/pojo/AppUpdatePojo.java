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
@Table(name = "AppUpdate")
@Schema(name = "AppUpdate", description = "")
public class AppUpdatePojo  {


	/**
	* @Description: 自增id
	*/
	@Column(name = "id")
	@Schema(name = "id", description = "自增id")
	private Integer id;


	/**
	* @Description: 版本号
	*/
	@Column(name = "version")
	@Schema(name = "version", description = "版本号")
	private String version;


	/**
	* @Description: 更新描述
	*/
	@Column(name = "update_desc")
	@Schema(name = "updateDesc", description = "更新描述")
	private String updateDesc;


	/**
	* @Description: 0：未发布 1：灰度发布 2：全网发布
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "0：未发布 1：灰度发布 2：全网发布")
	private Integer status;


	/**
	* @Description: 创建时间
	*/
	@Column(name = "create_time")
	@Schema(name = "createTime", description = "创建时间")
	private Long createTime;


	/**
	* @Description: 灰度uid
	*/
	@Column(name = "grayscale_uid")
	@Schema(name = "grayscaleUid", description = "灰度uid")
	private String grayscaleUid;


	/**
	* @Description: 文件类型 0：本地文件 1：外链
	*/
	@Column(name = "file_type")
	@Schema(name = "fileType", description = "文件类型 0：本地文件 1：外链")
	private Integer fileType;


	/**
	* @Description: 外链地址
	*/
	@Column(name = "outer_link")
	@Schema(name = "outerLink", description = "外链地址")
	private String outerLink;


	public AppUpdatePojo() {
	}

	public AppUpdatePojo(Integer id, String version, String updateDesc, Integer status, Long createTime, String grayscaleUid, Integer fileType, String outerLink) {
		this.id = id;
		this.version = version;
		this.updateDesc = updateDesc;
		this.status = status;
		this.createTime = createTime;
		this.grayscaleUid = grayscaleUid;
		this.fileType = fileType;
		this.outerLink = outerLink;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return this.version;
	}

	public void setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
	}

	public String getUpdateDesc() {
		return this.updateDesc;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCreateTime() {
		return this.createTime;
	}

	public void setGrayscaleUid(String grayscaleUid) {
		this.grayscaleUid = grayscaleUid;
	}

	public String getGrayscaleUid() {
		return this.grayscaleUid;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public Integer getFileType() {
		return this.fileType;
	}

	public void setOuterLink(String outerLink) {
		this.outerLink = outerLink;
	}

	public String getOuterLink() {
		return this.outerLink;
	}
}