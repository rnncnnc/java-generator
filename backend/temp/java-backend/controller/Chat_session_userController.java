package com.qinge.backend.controller;
import com.qinge.backend.entity.pojo.Chat_session_userPojo;
import com.qinge.backend.service.Chat_session_userService;
import com.qinge.backend.entity.query.Query;
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
@RequestMapping(value = "/chat_session_user")
@Tag(name = "Chat_session_user接口", description = "")
public class Chat_session_userController  {
	@Resource
	private Chat_session_userService<Chat_session_userPojo> chat_session_userService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<Chat_session_userPojo> query = new Query<>();
		
		List<Chat_session_userPojo> Chat_session_userList = chat_session_userService.select(query);
		
		return Result.success(Chat_session_userList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody Chat_session_userPojo pojo) {
		Query<Chat_session_userPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chat_session_userService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody Chat_session_userPojo pojo) {
		Query<Chat_session_userPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chat_session_userService.delete(query);
		
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
	Result updateByUser_id(@PathVariable("user_id") String user_id, @RequestBody Chat_session_userPojo np) {
		Query<Chat_session_userPojo> query = new Query<>();
		
		Chat_session_userPojo chat_session_userPojo = new Chat_session_userPojo();
		
		chat_session_userPojo.setUser_id(user_id);
		
		query.setPojo(chat_session_userPojo);
		
		chat_session_userService.update(query, np);
		
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
	Result updateByContact_id(@PathVariable("contact_id") String contact_id, @RequestBody Chat_session_userPojo np) {
		Query<Chat_session_userPojo> query = new Query<>();
		
		Chat_session_userPojo chat_session_userPojo = new Chat_session_userPojo();
		
		chat_session_userPojo.setContact_id(contact_id);
		
		query.setPojo(chat_session_userPojo);
		
		chat_session_userService.update(query, np);
		
		return Result.success();
	}


}