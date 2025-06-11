package com.qinge.bakcend.controller;
import com.qinge.bakcend.entity.pojo.ChatSessionUserPojo;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.service.ChatSessionUserService;
import com.qinge.bakcend.entity.vo.Result;

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
* @Data: 2025/06/11 21:33
* @Author: Lc
* @Description:
*/


@RestController
@RequestMapping(value = "/chatSessionUser")
@Tag(name = "ChatSessionUser接口", description = "")
public class ChatSessionUserController  {
	@Resource
	private ChatSessionUserService<ChatSessionUserPojo> chatSessionUserService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<ChatSessionUserPojo> query = new Query<>();
		
		List<ChatSessionUserPojo> ChatSessionUserList = chatSessionUserService.select(query);
		
		return Result.success(ChatSessionUserList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody ChatSessionUserPojo pojo) {
		Query<ChatSessionUserPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chatSessionUserService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody ChatSessionUserPojo pojo) {
		Query<ChatSessionUserPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chatSessionUserService.delete(query);
		
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
	Result updateByUserId(@PathVariable("userId") String userId, @RequestBody ChatSessionUserPojo np) {
		Query<ChatSessionUserPojo> query = new Query<>();
		
		ChatSessionUserPojo chatSessionUserPojo = new ChatSessionUserPojo();
		
		chatSessionUserPojo.setUserId(userId);
		
		query.setPojo(chatSessionUserPojo);
		
		chatSessionUserService.update(query, np);
		
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
	Result updateByContactId(@PathVariable("contactId") String contactId, @RequestBody ChatSessionUserPojo np) {
		Query<ChatSessionUserPojo> query = new Query<>();
		
		ChatSessionUserPojo chatSessionUserPojo = new ChatSessionUserPojo();
		
		chatSessionUserPojo.setContactId(contactId);
		
		query.setPojo(chatSessionUserPojo);
		
		chatSessionUserService.update(query, np);
		
		return Result.success();
	}


}