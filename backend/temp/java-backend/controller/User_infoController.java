package com.qinge.backend.controller;
import com.qinge.backend.entity.pojo.User_infoPojo;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.entity.vo.Result;
import com.qinge.backend.service.User_infoService;

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
* @Data: 2025/06/11 20:57
* @Author: Lc
* @Description:
*/


@RestController
@RequestMapping(value = "/user_info")
@Tag(name = "User_info接口", description = "")
public class User_infoController  {
	@Resource
	private User_infoService<User_infoPojo> user_infoService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<User_infoPojo> query = new Query<>();
		
		List<User_infoPojo> User_infoList = user_infoService.select(query);
		
		return Result.success(User_infoList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody User_infoPojo pojo) {
		Query<User_infoPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		user_infoService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody User_infoPojo pojo) {
		Query<User_infoPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		user_infoService.delete(query);
		
		return Result.success();
	}


	/**
	 * 根据user_id更新数据
	 * @param user_id
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据user_id更新数据", description = "根据条件更新数据")
	@PutMapping("/update/user_id/{user_id}")
	Result updateByUser_id(@PathVariable("user_id") String user_id, @RequestBody User_infoPojo np) {
		Query<User_infoPojo> query = new Query<>();
		
		User_infoPojo user_infoPojo = new User_infoPojo();
		
		user_infoPojo.setUser_id(user_id);
		
		query.setPojo(user_infoPojo);
		
		user_infoService.update(query, np);
		
		return Result.success();
	}


}