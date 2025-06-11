package com.qinge.backend.controller;
import com.qinge.backend.service.User_info_beautyService;
import com.qinge.backend.entity.pojo.User_info_beautyPojo;
import com.qinge.backend.entity.query.Query;
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
@RequestMapping(value = "/user_info_beauty")
@Tag(name = "User_info_beauty接口", description = "")
public class User_info_beautyController  {
	@Resource
	private User_info_beautyService<User_info_beautyPojo> user_info_beautyService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<User_info_beautyPojo> query = new Query<>();
		
		List<User_info_beautyPojo> User_info_beautyList = user_info_beautyService.select(query);
		
		return Result.success(User_info_beautyList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody User_info_beautyPojo pojo) {
		Query<User_info_beautyPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		user_info_beautyService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody User_info_beautyPojo pojo) {
		Query<User_info_beautyPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		user_info_beautyService.delete(query);
		
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
	Result updateById(@PathVariable("id") Integer id, @RequestBody User_info_beautyPojo np) {
		Query<User_info_beautyPojo> query = new Query<>();
		
		User_info_beautyPojo user_info_beautyPojo = new User_info_beautyPojo();
		
		user_info_beautyPojo.setId(id);
		
		query.setPojo(user_info_beautyPojo);
		
		user_info_beautyService.update(query, np);
		
		return Result.success();
	}


}