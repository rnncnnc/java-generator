package com.qinge.playerbackend.entity.pojo;

import java.lang.Long;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;
import jakarta.persistence.Id;


/**
* @Data: 2025/06/11 00:11
 * @Author: Lc
 * @Description:
 */


@Entity
@Table(name = "FictionType")
@Schema(name = "FictionType", description = "")
public class FictionTypePojo  {


	/**
	* @Description: type_id
	*/
	@Column(name = "id")
	@Schema(name = "id", description = "type_id")
	@Id
	private Long id;


	/**
	* @Description: 1 精选   2 休闲
	*/
	@Column(name = "type")
	@Schema(name = "type", description = "1 精选   2 休闲")
	private String type;


	public FictionTypePojo() {
	}

	public FictionTypePojo(Long id, String type) {
		this.id = id;
		this.type = type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
}