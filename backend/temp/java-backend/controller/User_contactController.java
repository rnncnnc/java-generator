package com.qinge.backend.controller;
import com.qinge.backend.service.User_contactService;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.entity.pojo.User_contactPojo;
import com.qinge.backend.entity.vo.Result;

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
@RequestMapping(value = "/user_contact")
@Tag(name = "User_contact接口", description = "")
public class User_contactController  {
	@Resource
	private User_contactService<User_contactPojo> user_contactService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<User_contactPojo> query = new Query<>();
		
		List<User_contactPojo> User_contactList = user_contactService.select(query);
		
		return Result.success(User_contactList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody User_contactPojo pojo) {
		Query<User_contactPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		user_contactService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody User_contactPojo pojo) {
		Query<User_contactPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		user_contactService.delete(query);
		
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
	Result updateByUser_id(@PathVariable("user_id") String user_id, @RequestBody User_contactPojo np) {
		Query<User_contactPojo> query = new Query<>();
		
		User_contactPojo user_contactPojo = new User_contactPojo();
		
		user_contactPojo.setUser_id(user_id);
		
		query.setPojo(user_contactPojo);
		
		user_contactService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据contact_id更新数据
	 * @param contact_id
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据contact_id更新数据", description = "根据条件更新数据")
	@PutMapping("/update/contact_id/{contact_id}")
	Result updateByContact_id(@PathVariable("contact_id") String contact_id, @RequestBody User_contactPojo np) {
		Query<User_contactPojo> query = new Query<>();
		
		User_contactPojo user_contactPojo = new User_contactPojo();
		
		user_contactPojo.setContact_id(contact_id);
		
		query.setPojo(user_contactPojo);
		
		user_contactService.update(query, np);
		
		return Result.success();
	}


}