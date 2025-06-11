package com.qinge.backend.controller;
import com.qinge.backend.service.Chat_sessionService;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.entity.pojo.Chat_sessionPojo;
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
@RequestMapping(value = "/chat_session")
@Tag(name = "Chat_session接口", description = "")
public class Chat_sessionController  {
	@Resource
	private Chat_sessionService<Chat_sessionPojo> chat_sessionService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<Chat_sessionPojo> query = new Query<>();
		
		List<Chat_sessionPojo> Chat_sessionList = chat_sessionService.select(query);
		
		return Result.success(Chat_sessionList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody Chat_sessionPojo pojo) {
		Query<Chat_sessionPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chat_sessionService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody Chat_sessionPojo pojo) {
		Query<Chat_sessionPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chat_sessionService.delete(query);
		
		return Result.success();
	}


	/**
	 * 根据session_id更新数据
	 * @param session_id
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据session_id更新数据", description = "根据条件更新数据")
	@PutMapping("/update/session_id/{session_id}")
	Result updateBySession_id(@PathVariable("session_id") String session_id, @RequestBody Chat_sessionPojo np) {
		Query<Chat_sessionPojo> query = new Query<>();
		
		Chat_sessionPojo chat_sessionPojo = new Chat_sessionPojo();
		
		chat_sessionPojo.setSession_id(session_id);
		
		query.setPojo(chat_sessionPojo);
		
		chat_sessionService.update(query, np);
		
		return Result.success();
	}


}