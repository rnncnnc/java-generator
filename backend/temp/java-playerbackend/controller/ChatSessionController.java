package com.qinge.playerbackend.controller;
import com.qinge.playerbackend.entity.vo.Result;
import com.qinge.playerbackend.entity.query.Query;
import com.qinge.playerbackend.service.ChatSessionService;
import com.qinge.playerbackend.entity.pojo.ChatSessionPojo;

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
@RequestMapping(value = "/chatSession")
@Tag(name = "ChatSession接口", description = "会话信息")
public class ChatSessionController  {
	@Resource
	private ChatSessionService<ChatSessionPojo> chatSessionService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<ChatSessionPojo> query = new Query<>();
		
		List<ChatSessionPojo> ChatSessionList = chatSessionService.select(query);
		
		return Result.success(ChatSessionList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody ChatSessionPojo pojo) {
		Query<ChatSessionPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chatSessionService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody ChatSessionPojo pojo) {
		Query<ChatSessionPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chatSessionService.delete(query);
		
		return Result.success();
	}


	/**
	 * 根据sessionId更新数据
	 * @param sessionId
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据sessionId更新数据", description = "根据条件更新数据")
	@PutMapping("/update/sessionId/{sessionId}")
	Result updateBySessionId(@PathVariable("sessionId") String sessionId, @RequestBody ChatSessionPojo np) {
		Query<ChatSessionPojo> query = new Query<>();
		
		ChatSessionPojo chatSessionPojo = new ChatSessionPojo();
		
		chatSessionPojo.setSessionId(sessionId);
		
		query.setPojo(chatSessionPojo);
		
		chatSessionService.update(query, np);
		
		return Result.success();
	}


}