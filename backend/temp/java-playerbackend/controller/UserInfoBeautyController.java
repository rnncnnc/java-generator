package com.qinge.playerbackend.controller;
import com.qinge.playerbackend.service.UserInfoBeautyService;
import com.qinge.playerbackend.entity.vo.Result;
import com.qinge.playerbackend.entity.pojo.UserInfoBeautyPojo;
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
@RequestMapping(value = "/userInfoBeauty")
@Tag(name = "UserInfoBeauty接口", description = "靓号表")
public class UserInfoBeautyController  {
	@Resource
	private UserInfoBeautyService<UserInfoBeautyPojo> userInfoBeautyService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<UserInfoBeautyPojo> query = new Query<>();
		
		List<UserInfoBeautyPojo> UserInfoBeautyList = userInfoBeautyService.select(query);
		
		return Result.success(UserInfoBeautyList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody UserInfoBeautyPojo pojo) {
		Query<UserInfoBeautyPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		userInfoBeautyService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody UserInfoBeautyPojo pojo) {
		Query<UserInfoBeautyPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		userInfoBeautyService.delete(query);
		
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
	Result updateById(@PathVariable("id") Integer id, @RequestBody UserInfoBeautyPojo np) {
		Query<UserInfoBeautyPojo> query = new Query<>();
		
		UserInfoBeautyPojo userInfoBeautyPojo = new UserInfoBeautyPojo();
		
		userInfoBeautyPojo.setId(id);
		
		query.setPojo(userInfoBeautyPojo);
		
		userInfoBeautyService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据email更新数据
	 * @param email
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据email更新数据", description = "根据条件更新数据")
	@PutMapping("/update/email/{email}")
	Result updateByEmail(@PathVariable("email") String email, @RequestBody UserInfoBeautyPojo np) {
		Query<UserInfoBeautyPojo> query = new Query<>();
		
		UserInfoBeautyPojo userInfoBeautyPojo = new UserInfoBeautyPojo();
		
		userInfoBeautyPojo.setEmail(email);
		
		query.setPojo(userInfoBeautyPojo);
		
		userInfoBeautyService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据userId更新数据
	 * @param userId
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据userId更新数据", description = "根据条件更新数据")
	@PutMapping("/update/userId/{userId}")
	Result updateByUserId(@PathVariable("userId") String userId, @RequestBody UserInfoBeautyPojo np) {
		Query<UserInfoBeautyPojo> query = new Query<>();
		
		UserInfoBeautyPojo userInfoBeautyPojo = new UserInfoBeautyPojo();
		
		userInfoBeautyPojo.setUserId(userId);
		
		query.setPojo(userInfoBeautyPojo);
		
		userInfoBeautyService.update(query, np);
		
		return Result.success();
	}


}