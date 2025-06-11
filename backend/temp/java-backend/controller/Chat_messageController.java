package com.qinge.backend.controller;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.entity.pojo.Chat_messagePojo;
import com.qinge.backend.entity.vo.Result;
import com.qinge.backend.service.Chat_messageService;

import java.lang.Long;
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
@RequestMapping(value = "/chat_message")
@Tag(name = "Chat_message接口", description = "")
public class Chat_messageController  {
	@Resource
	private Chat_messageService<Chat_messagePojo> chat_messageService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<Chat_messagePojo> query = new Query<>();
		
		List<Chat_messagePojo> Chat_messageList = chat_messageService.select(query);
		
		return Result.success(Chat_messageList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody Chat_messagePojo pojo) {
		Query<Chat_messagePojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chat_messageService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody Chat_messagePojo pojo) {
		Query<Chat_messagePojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chat_messageService.delete(query);
		
		return Result.success();
	}


	/**
	 * 根据message_id更新数据
	 * @param message_id
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据message_id更新数据", description = "根据条件更新数据")
	@PutMapping("/update/message_id/{message_id}")
	Result updateByMessage_id(@PathVariable("message_id") Long message_id, @RequestBody Chat_messagePojo np) {
		Query<Chat_messagePojo> query = new Query<>();
		
		Chat_messagePojo chat_messagePojo = new Chat_messagePojo();
		
		chat_messagePojo.setMessage_id(message_id);
		
		query.setPojo(chat_messagePojo);
		
		chat_messageService.update(query, np);
		
		return Result.success();
	}


}