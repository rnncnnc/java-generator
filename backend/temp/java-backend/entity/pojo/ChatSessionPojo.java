package com.qinge.backend.entity.pojo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.Long;
import java.lang.String;
import jakarta.persistence.Table;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */


@Entity
@Table(name = "ChatSession")
@Schema(name = "ChatSession", description = "会话信息")
public class ChatSession {

	/**
	 * 会话id
	 */
    @Column(name = "sessionId")
    @Schema(description = "会话id")
    private String sessionId;



	/**
	 * 最后接收的消息
	 */
    @Column(name = "lastMessage")
    @Schema(description = "最后接收的消息")
    private String lastMessage;



	/**
	 * 最后接收消息时间毫秒
	 */
    @Column(name = "lastReceiveTime")
    @Schema(description = "最后接收消息时间毫秒")
    private Long lastReceiveTime;



    public String getsessionId() {
        return sessionId;

    }

    public void setsessionId(String sessionId) {
        this.sessionId = sessionId;

    }



    public String getlastMessage() {
        return lastMessage;

    }

    public void setlastMessage(String lastMessage) {
        this.lastMessage = lastMessage;

    }



    public Long getlastReceiveTime() {
        return lastReceiveTime;

    }

    public void setlastReceiveTime(Long lastReceiveTime) {
        this.lastReceiveTime = lastReceiveTime;

    }



}