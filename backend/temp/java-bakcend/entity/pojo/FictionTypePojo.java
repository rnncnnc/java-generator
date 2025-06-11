package com.qinge.bakcend.entity.pojo;

import java.lang.Integer;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;
import jakarta.persistence.Id;


/**
* @Data: 2025/06/11 21:33
 * @Author: Lc
 * @Description:
 */


@Entity
@Table(name = "FictionType")
@Schema(name = "FictionType", description = "小说类型")
public class FictionTypePojo  {


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
	@Schema(name = "typeName", description = "")
	private String typeName;


	public FictionTypePojo() {
	}

	public FictionTypePojo(Integer id, String typeName) {
		this.id = id;
		this.typeName = typeName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return this.typeName;
	}
}