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
@Table(name = "AppUpdate")
@Schema(name = "AppUpdate", description = "")
public class AppUpdate {

	/**
	 * 自增id
	 */
    @Column(name = "id")
    @Schema(description = "自增id")
    private Integer id;



	/**
	 * 版本号
	 */
    @Column(name = "version")
    @Schema(description = "版本号")
    private String version;



	/**
	 * 更新描述
	 */
    @Column(name = "updateDesc")
    @Schema(description = "更新描述")
    private String updateDesc;



	/**
	 * 0：未发布 1：灰度发布 2：全网发布
	 */
    @Column(name = "status")
    @Schema(description = "0：未发布 1：灰度发布 2：全网发布")
    private Integer status;



	/**
	 * 创建时间
	 */
    @Column(name = "createTime")
    @Schema(description = "创建时间")
    private Long createTime;



	/**
	 * 灰度uid
	 */
    @Column(name = "grayscaleUid")
    @Schema(description = "灰度uid")
    private String grayscaleUid;



	/**
	 * 文件类型 0：本地文件 1：外链
	 */
    @Column(name = "fileType")
    @Schema(description = "文件类型 0：本地文件 1：外链")
    private Integer fileType;



	/**
	 * 外链地址
	 */
    @Column(name = "outerLink")
    @Schema(description = "外链地址")
    private String outerLink;



    public Integer getid() {
        return id;

    }

    public void setid(Integer id) {
        this.id = id;

    }



    public String getversion() {
        return version;

    }

    public void setversion(String version) {
        this.version = version;

    }



    public String getupdateDesc() {
        return updateDesc;

    }

    public void setupdateDesc(String updateDesc) {
        this.updateDesc = updateDesc;

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



    public String getgrayscaleUid() {
        return grayscaleUid;

    }

    public void setgrayscaleUid(String grayscaleUid) {
        this.grayscaleUid = grayscaleUid;

    }



    public Integer getfileType() {
        return fileType;

    }

    public void setfileType(Integer fileType) {
        this.fileType = fileType;

    }



    public String getouterLink() {
        return outerLink;

    }

    public void setouterLink(String outerLink) {
        this.outerLink = outerLink;

    }



}