package com.qinge.backend.controller;
import com.qinge.backend.entity.pojo.User_contact_applyPojo;
import com.qinge.backend.service.User_contact_applyService;
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
@RequestMapping(value = "/user_contact_apply")
@Tag(name = "User_contact_apply接口", description = "")
public class User_contact_applyController  {
	@Resource
	private User_contact_applyService<User_contact_applyPojo> user_contact_applyService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<User_contact_applyPojo> query = new Query<>();
		
		List<User_contact_applyPojo> User_contact_applyList = user_contact_applyService.select(query);
		
		return Result.success(User_contact_applyList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody User_contact_applyPojo pojo) {
		Query<User_contact_applyPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		user_contact_applyService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody User_contact_applyPojo pojo) {
		Query<User_contact_applyPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		user_contact_applyService.delete(query);
		
		return Result.success();
	}


	/**
	 * 根据apply_id更新数据
	 * @param apply_id
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据apply_id更新数据", description = "根据条件更新数据")
	@PutMapping("/update/apply_id/{apply_id}")
	Result updateByApply_id(@PathVariable("apply_id") Integer apply_id, @RequestBody User_contact_applyPojo np) {
		Query<User_contact_applyPojo> query = new Query<>();
		
		User_contact_applyPojo user_contact_applyPojo = new User_contact_applyPojo();
		
		user_contact_applyPojo.setApply_id(apply_id);
		
		query.setPojo(user_contact_applyPojo);
		
		user_contact_applyService.update(query, np);
		
		return Result.success();
	}


}