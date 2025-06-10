package com.qinge.playerbackend.controller;
import com.qinge.playerbackend.service.UserContactService;
import com.qinge.playerbackend.entity.vo.Result;
import com.qinge.playerbackend.entity.query.Query;
import com.qinge.playerbackend.entity.pojo.UserContactPojo;

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
@RequestMapping(value = "/userContact")
@Tag(name = "UserContact接口", description = "联系人")
public class UserContactController  {
	@Resource
	private UserContactService<UserContactPojo> userContactService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<UserContactPojo> query = new Query<>();
		
		List<UserContactPojo> UserContactList = userContactService.select(query);
		
		return Result.success(UserContactList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody UserContactPojo pojo) {
		Query<UserContactPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		userContactService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody UserContactPojo pojo) {
		Query<UserContactPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		userContactService.delete(query);
		
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
	Result updateByUserId(@PathVariable("userId") String userId, @RequestBody UserContactPojo np) {
		Query<UserContactPojo> query = new Query<>();
		
		UserContactPojo userContactPojo = new UserContactPojo();
		
		userContactPojo.setUserId(userId);
		
		query.setPojo(userContactPojo);
		
		userContactService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据contactId更新数据
	 * @param contactId
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据contactId更新数据", description = "根据条件更新数据")
	@PutMapping("/update/contactId/{contactId}")
	Result updateByContactId(@PathVariable("contactId") String contactId, @RequestBody UserContactPojo np) {
		Query<UserContactPojo> query = new Query<>();
		
		UserContactPojo userContactPojo = new UserContactPojo();
		
		userContactPojo.setContactId(contactId);
		
		query.setPojo(userContactPojo);
		
		userContactService.update(query, np);
		
		return Result.success();
	}


}