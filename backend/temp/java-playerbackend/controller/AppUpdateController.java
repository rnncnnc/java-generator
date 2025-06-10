package com.qinge.playerbackend.controller;
import com.qinge.playerbackend.entity.pojo.AppUpdatePojo;
import com.qinge.playerbackend.service.AppUpdateService;
import com.qinge.playerbackend.entity.vo.Result;
import com.qinge.playerbackend.entity.query.Query;

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
import java.lang.String;


/**
* @Data: 2025/06/10 18:54
* @Author: Lc
* @Description:
*/


@RestController
@RequestMapping(value = "/appUpdate")
@Tag(name = "AppUpdate接口", description = "")
public class AppUpdateController  {
	@Resource
	private AppUpdateService<AppUpdatePojo> appUpdateService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<AppUpdatePojo> query = new Query<>();
		
		List<AppUpdatePojo> AppUpdateList = appUpdateService.select(query);
		
		return Result.success(AppUpdateList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody AppUpdatePojo pojo) {
		Query<AppUpdatePojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		appUpdateService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody AppUpdatePojo pojo) {
		Query<AppUpdatePojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		appUpdateService.delete(query);
		
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
	Result updateById(@PathVariable("id") Integer id, @RequestBody AppUpdatePojo np) {
		Query<AppUpdatePojo> query = new Query<>();
		
		AppUpdatePojo appUpdatePojo = new AppUpdatePojo();
		
		appUpdatePojo.setId(id);
		
		query.setPojo(appUpdatePojo);
		
		appUpdateService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据version更新数据
	 * @param version
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据version更新数据", description = "根据条件更新数据")
	@PutMapping("/update/version/{version}")
	Result updateByVersion(@PathVariable("version") String version, @RequestBody AppUpdatePojo np) {
		Query<AppUpdatePojo> query = new Query<>();
		
		AppUpdatePojo appUpdatePojo = new AppUpdatePojo();
		
		appUpdatePojo.setVersion(version);
		
		query.setPojo(appUpdatePojo);
		
		appUpdateService.update(query, np);
		
		return Result.success();
	}


}