package com.qinge.backend.controller;
import com.qinge.backend.entity.pojo.Fiction_typePojo;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.service.Fiction_typeService;
import com.qinge.backend.entity.vo.Result;

import java.lang.Integer;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Operation;


/**
* @Data: 2025/06/11 20:57
* @Author: Lc
* @Description:
*/


@RestController
@RequestMapping(value = "/fiction_type")
@Tag(name = "Fiction_type接口", description = "小说类型")
public class Fiction_typeController  {
	@Resource
	private Fiction_typeService<Fiction_typePojo> fiction_typeService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<Fiction_typePojo> query = new Query<>();
		
		List<Fiction_typePojo> Fiction_typeList = fiction_typeService.select(query);
		
		return Result.success(Fiction_typeList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody Fiction_typePojo pojo) {
		Query<Fiction_typePojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		fiction_typeService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody Fiction_typePojo pojo) {
		Query<Fiction_typePojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		fiction_typeService.delete(query);
		
		return Result.success();
	}


	/**
	 * 根据id更新数据
	 * @param id
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据id更新数据", description = "根据条件更新数据")
	@PutMapping("/update/id/{id}")
	Result updateById(@PathVariable("id") Integer id, @RequestBody Fiction_typePojo np) {
		Query<Fiction_typePojo> query = new Query<>();
		
		Fiction_typePojo fiction_typePojo = new Fiction_typePojo();
		
		fiction_typePojo.setId(id);
		
		query.setPojo(fiction_typePojo);
		
		fiction_typeService.update(query, np);
		
		return Result.success();
	}


}