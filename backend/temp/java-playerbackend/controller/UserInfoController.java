package com.qinge.playerbackend.controller;
import com.qinge.playerbackend.entity.vo.Result;
import com.qinge.playerbackend.entity.query.Query;
import com.qinge.playerbackend.entity.pojo.UserInfoPojo;
import com.qinge.playerbackend.service.UserInfoService;

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
@RequestMapping(value = "/userInfo")
@Tag(name = "UserInfo接口", description = "用户信息表")
public class UserInfoController  {
	@Resource
	private UserInfoService<UserInfoPojo> userInfoService;


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


	/**
	 * 根据email更新数据
	 * @param email
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据email更新数据", description = "根据条件更新数据")
	@PutMapping("/update/email/{email}")
	Result updateByEmail(@PathVariable("email") String email, @RequestBody UserInfoPojo np) {
		Query<UserInfoPojo> query = new Query<>();
		
		UserInfoPojo userInfoPojo = new UserInfoPojo();
		
		userInfoPojo.setEmail(email);
		
		query.setPojo(userInfoPojo);
		
		userInfoService.update(query, np);
		
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
	Result updateByUserId(@PathVariable("userId") String userId, @RequestBody UserInfoPojo np) {
		Query<UserInfoPojo> query = new Query<>();
		
		UserInfoPojo userInfoPojo = new UserInfoPojo();
		
		userInfoPojo.setUserId(userId);
		
		query.setPojo(userInfoPojo);
		
		userInfoService.update(query, np);
		
		return Result.success();
	}


}