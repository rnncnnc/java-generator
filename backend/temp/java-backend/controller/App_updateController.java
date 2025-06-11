package com.qinge.backend.controller;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.service.App_updateService;
import com.qinge.backend.entity.pojo.App_updatePojo;
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
@RequestMapping(value = "/app_update")
@Tag(name = "App_update接口", description = "")
public class App_updateController  {
	@Resource
	private App_updateService<App_updatePojo> app_updateService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<App_updatePojo> query = new Query<>();
		
		List<App_updatePojo> App_updateList = app_updateService.select(query);
		
		return Result.success(App_updateList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody App_updatePojo pojo) {
		Query<App_updatePojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		app_updateService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody App_updatePojo pojo) {
		Query<App_updatePojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		app_updateService.delete(query);
		
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
	Result updateById(@PathVariable("id") Integer id, @RequestBody App_updatePojo np) {
		Query<App_updatePojo> query = new Query<>();
		
		App_updatePojo app_updatePojo = new App_updatePojo();
		
		app_updatePojo.setId(id);
		
		query.setPojo(app_updatePojo);
		
		app_updateService.update(query, np);
		
		return Result.success();
	}


}