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
@Table(name = "AppUpdate")
@Schema(name = "AppUpdate", description = "")
public class AppUpdatePojo  {


	/**
	* @Description: 自增id
	*/
	@Column(name = "id")
	@Schema(name = "id", description = "自增id")
	@Id
	private Integer id;


	/**
	* @Description: 
	*/
	@Column(name = "version")
	@Schema(name = "version", description = "")
	private String version;


	/**
	* @Description: 
	*/
	@Column(name = "update_desc")
	@Schema(name = "updateDesc", description = "")
	private String updateDesc;


	/**
	* @Description: 
	*/
	@Column(name = "status")
	@Schema(name = "status", description = "")
	private Integer status;


	/**
	* @Description: 
	*/
	@Column(name = "create_time")
	@Schema(name = "createTime", description = "")
	private Long createTime;


	/**
	* @Description: 
	*/
	@Column(name = "grayscale_uid")
	@Schema(name = "grayscaleUid", description = "")
	private String grayscaleUid;


	/**
	* @Description: 
	*/
	@Column(name = "file_type")
	@Schema(name = "fileType", description = "")
	private Integer fileType;


	/**
	* @Description: 
	*/
	@Column(name = "outer_link")
	@Schema(name = "outerLink", description = "")
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