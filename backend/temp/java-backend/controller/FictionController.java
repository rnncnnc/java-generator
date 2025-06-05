package com.qinge.backend.controller;
import com.qinge.backend.controller.BaseController;
import com.qinge.backend.entity.pojo.FunctionPojo;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.mapper.BaseMapper;
import com.qinge.backend.entity.vo.Result;
import com.qinge.backend.entity.pojo.Pojo;
import com.qinge.backend.entity.pojo.FictionPojo;

import java.lang.Integer;
import jakarta.persistence.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import java.lang.String;
import io.swagger.v3.oas.annotations.Operation;


/**
* @Data: 2025/5/26 22:38
 * @Author: Lc
 * @Description:
 */


@Entity(name = "Fiction")
@Table(name = "Fiction")
@Schema(name = "Fiction", description = "小说")
public class FictionController<T extends FictionPojo, E> extend BaseController<FictionPojo> implements BaseMapper<FictionPojo> {


	/**
	 * 会话id
	 */
	@Column(name = "id")
	@Schema(description = "小说id")
	private Integer id;


	/**
	 * 小说名称
	 */
	@Column(name = "novel")
	@Schema(description = "小说名称")
	private String novel;


	/**
	 * 查找符合类型的所有数据
	 * @param query
	 * @return
	*/
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping(value = "/delete")
	public <T extends Pojo, E extends Entity>Result delete(
			@RequestBody
			@RequestParam(name = "pojo", required = true)
			FunctionPojo pojo) {
		Query<FictionPojo> query = new Query<>();
		query.setPojo(pojo);
		testService.delete(query);
		
		return Result.success();
	}


	public class() {
	}

	public class(Integer id, String novel) {
		this.id = id
		this.novel = novel
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id
	}

	public void setNovel(String novel) {
		this.novel = novel;
	}

	public String getNovel() {
		return this.novel
	}
}