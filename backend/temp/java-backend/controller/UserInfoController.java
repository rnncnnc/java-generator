package com.qinge.backend.controller;
import com.qinge.backend.entity.pojo.UserInfoPojo;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.entity.vo.Result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.lang.Integer;
import org.springframework.web.bind.annotation.PutMapping;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;


/**
* @Data: 2025/06/10 16:08
* @Author: Lc
* @Description:
*/


@RestController
@RequestMapping(value = "/userInfo")
@Tag(name = "UserInfo接口", description = "用户信息表")
public class UserInfoController  {
	@Resource
	private UserInfoService userInfoService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<UserInfoPojo> query = new Query<>();
		
		List<UserInfoPojo> UserInfoList = userInfoService.select(query);
		
		return Result.success(UserInfoList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody UserInfoPojo pojo) {
		Query<UserInfoPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		userInfoService.insert(query);
		
		return Result.success();
	}


	/**
	 * 根据id更新数据
	 * @param id
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据id更新数据", description = "根据条件更新数据")
	@PutMapping("/update/{id}")
	Result update(@PathVariable("id") Integer id, @RequestBody UserInfoPojo np) {
		Query<UserInfoPojo> query = new Query<>();
		
		UserInfoPojo userInfoPojo = new UserInfoPojo();
		
		userInfoPojo.setId(id);
		
		query.setPojo(userInfoPojo);
		
		userInfoService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody UserInfoPojo pojo) {
		Query<UserInfoPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		userInfoService.delete(query);
		
		return Result.success();
	}


}