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
@Table(name = "ChatMessage")
@Schema(name = "ChatMessage", description = "聊天消息")
public class ChatMessage {

	/**
	 * 消息自增id
	 */
    @Column(name = "messageId")
    @Schema(description = "消息自增id")
    private Long messageId;



	/**
	 * 会话id
	 */
    @Column(name = "sessionId")
    @Schema(description = "会话id")
    private String sessionId;



	/**
	 * 消息类型
	 */
    @Column(name = "messageType")
    @Schema(description = "消息类型")
    private Integer messageType;



	/**
	 * 消息内容
	 */
    @Column(name = "messageContent")
    @Schema(description = "消息内容")
    private String messageContent;



	/**
	 * 发送人id
	 */
    @Column(name = "sendUserId")
    @Schema(description = "发送人id")
    private String sendUserId;



	/**
	 * 发送人昵称
	 */
    @Column(name = "sendUserNickName")
    @Schema(description = "发送人昵称")
    private String sendUserNickName;



	/**
	 * 发送时间
	 */
    @Column(name = "sendTime")
    @Schema(description = "发送时间")
    private Long sendTime;



	/**
	 * 接收联系人id
	 */
    @Column(name = "contactId")
    @Schema(description = "接收联系人id")
    private String contactId;



	/**
	 * 联系人类型 0：单聊   1：群聊
	 */
    @Column(name = "contactType")
    @Schema(description = "联系人类型 0：单聊   1：群聊")
    private Integer contactType;



	/**
	 * 文件大小
	 */
    @Column(name = "fileSize")
    @Schema(description = "文件大小")
    private Long fileSize;



	/**
	 * 文件名称
	 */
    @Column(name = "fileName")
    @Schema(description = "文件名称")
    private String fileName;



	/**
	 * 文件类型
	 */
    @Column(name = "fileType")
    @Schema(description = "文件类型")
    private Integer fileType;



	/**
	 * 状态    0：正在发送     1：已发送
	 */
    @Column(name = "status")
    @Schema(description = "状态    0：正在发送     1：已发送")
    private Integer status;



    public Long getmessageId() {
        return messageId;

    }

    public void setmessageId(Long messageId) {
        this.messageId = messageId;

    }



    public String getsessionId() {
        return sessionId;

    }

    public void setsessionId(String sessionId) {
        this.sessionId = sessionId;

    }



    public Integer getmessageType() {
        return messageType;

    }

    public void setmessageType(Integer messageType) {
        this.messageType = messageType;

    }



    public String getmessageContent() {
        return messageContent;

    }

    public void setmessageContent(String messageContent) {
        this.messageContent = messageContent;

    }



    public String getsendUserId() {
        return sendUserId;

    }

    public void setsendUserId(String sendUserId) {
        this.sendUserId = sendUserId;

    }



    public String getsendUserNickName() {
        return sendUserNickName;

    }

    public void setsendUserNickName(String sendUserNickName) {
        this.sendUserNickName = sendUserNickName;

    }



    public Long getsendTime() {
        return sendTime;

    }

    public void setsendTime(Long sendTime) {
        this.sendTime = sendTime;

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



    public Long getfileSize() {
        return fileSize;

    }

    public void setfileSize(Long fileSize) {
        this.fileSize = fileSize;

    }



    public String getfileName() {
        return fileName;

    }

    public void setfileName(String fileName) {
        this.fileName = fileName;

    }



    public Integer getfileType() {
        return fileType;

    }

    public void setfileType(Integer fileType) {
        this.fileType = fileType;

    }



    public Integer getstatus() {
        return status;

    }

    public void setstatus(Integer status) {
        this.status = status;

    }



}