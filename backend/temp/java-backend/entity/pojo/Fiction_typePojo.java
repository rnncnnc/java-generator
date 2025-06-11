package com.qinge.backend.entity.pojo;

import java.lang.Integer;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;
import jakarta.persistence.Id;


/**
* @Data: 2025/06/11 20:57
 * @Author: Lc
 * @Description:
 */


@Entity
@Table(name = "Fiction_type")
@Schema(name = "Fiction_type", description = "小说类型")
public class Fiction_typePojo  {


	/**
	* @Description: id
	*/
	@Column(name = "id")
	@Schema(name = "id", description = "id")
	@Id
	private Integer id;


	/**
	* @Description: 
	*/
	@Column(name = "type_name")
	@Schema(name = "type_name", description = "")
	private String type_name;


	public Fiction_typePojo() {
	}

	public Fiction_typePojo(Integer id, String type_name) {
		this.id = id;
		this.type_name = type_name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getType_name() {
		return this.type_name;
	}
}