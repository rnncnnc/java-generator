package com.qinge.backend.entity.pojo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;
import jakarta.persistence.Table;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */


@Entity
@Table(name = "ChatSessionUser")
@Schema(name = "ChatSessionUser", description = "")
public class ChatSessionUser {

	/**
	 * 用户id
	 */
    @Column(name = "userId")
    @Schema(description = "用户id")
    private String userId;



	/**
	 * 联系人id
	 */
    @Column(name = "contactId")
    @Schema(description = "联系人id")
    private String contactId;



	/**
	 * 会话id
	 */
    @Column(name = "sessionId")
    @Schema(description = "会话id")
    private String sessionId;



	/**
	 * 联系人名称
	 */
    @Column(name = "contactName")
    @Schema(description = "联系人名称")
    private String contactName;



    public String getuserId() {
        return userId;

    }

    public void setuserId(String userId) {
        this.userId = userId;

    }



    public String getcontactId() {
        return contactId;

    }

    public void setcontactId(String contactId) {
        this.contactId = contactId;

    }



    public String getsessionId() {
        return sessionId;

    }

    public void setsessionId(String sessionId) {
        this.sessionId = sessionId;

    }



    public String getcontactName() {
        return contactName;

    }

    public void setcontactName(String contactName) {
        this.contactName = contactName;

    }



}