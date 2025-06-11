package com.qinge.bakcend.controller;
import com.qinge.bakcend.entity.pojo.FictionsPojo;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.service.FictionsService;
import com.qinge.bakcend.entity.vo.Result;

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
* @Data: 2025/06/11 21:33
* @Author: Lc
* @Description:
*/


@RestController
@RequestMapping(value = "/fictions")
@Tag(name = "Fictions接口", description = "小说")
public class FictionsController  {
	@Resource
	private FictionsService<FictionsPojo> fictionsService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<FictionsPojo> query = new Query<>();
		
		List<FictionsPojo> FictionsList = fictionsService.select(query);
		
		return Result.success(FictionsList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody FictionsPojo pojo) {
		Query<FictionsPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		fictionsService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody FictionsPojo pojo) {
		Query<FictionsPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		fictionsService.delete(query);
		
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
	Result updateById(@PathVariable("id") Integer id, @RequestBody FictionsPojo np) {
		Query<FictionsPojo> query = new Query<>();
		
		FictionsPojo fictionsPojo = new FictionsPojo();
		
		fictionsPojo.setId(id);
		
		query.setPojo(fictionsPojo);
		
		fictionsService.update(query, np);
		
		return Result.success();
	}


}