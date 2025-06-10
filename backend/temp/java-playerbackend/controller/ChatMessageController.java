package com.qinge.playerbackend.controller;
import com.qinge.playerbackend.entity.vo.Result;
import com.qinge.playerbackend.entity.pojo.ChatMessagePojo;
import com.qinge.playerbackend.entity.query.Query;
import com.qinge.playerbackend.service.ChatMessageService;

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
import java.lang.String;


/**
* @Data: 2025/06/10 18:54
* @Author: Lc
* @Description:
*/


@RestController
@RequestMapping(value = "/chatMessage")
@Tag(name = "ChatMessage接口", description = "聊天消息")
public class ChatMessageController  {
	@Resource
	private ChatMessageService<ChatMessagePojo> chatMessageService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<ChatMessagePojo> query = new Query<>();
		
		List<ChatMessagePojo> ChatMessageList = chatMessageService.select(query);
		
		return Result.success(ChatMessageList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody ChatMessagePojo pojo) {
		Query<ChatMessagePojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chatMessageService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody ChatMessagePojo pojo) {
		Query<ChatMessagePojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chatMessageService.delete(query);
		
		return Result.success();
	}


	/**
	 * 根据messageId更新数据
	 * @param messageId
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据messageId更新数据", description = "根据条件更新数据")
	@PutMapping("/update/messageId/{messageId}")
	Result updateByMessageId(@PathVariable("messageId") Long messageId, @RequestBody ChatMessagePojo np) {
		Query<ChatMessagePojo> query = new Query<>();
		
		ChatMessagePojo chatMessagePojo = new ChatMessagePojo();
		
		chatMessagePojo.setMessageId(messageId);
		
		query.setPojo(chatMessagePojo);
		
		chatMessageService.update(query, np);
		
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
	Result updateBySessionId(@PathVariable("sessionId") String sessionId, @RequestBody ChatMessagePojo np) {
		Query<ChatMessagePojo> query = new Query<>();
		
		ChatMessagePojo chatMessagePojo = new ChatMessagePojo();
		
		chatMessagePojo.setSessionId(sessionId);
		
		query.setPojo(chatMessagePojo);
		
		chatMessageService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据sendUserId更新数据
	 * @param sendUserId
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据sendUserId更新数据", description = "根据条件更新数据")
	@PutMapping("/update/sendUserId/{sendUserId}")
	Result updateBySendUserId(@PathVariable("sendUserId") String sendUserId, @RequestBody ChatMessagePojo np) {
		Query<ChatMessagePojo> query = new Query<>();
		
		ChatMessagePojo chatMessagePojo = new ChatMessagePojo();
		
		chatMessagePojo.setSendUserId(sendUserId);
		
		query.setPojo(chatMessagePojo);
		
		chatMessageService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据sendTime更新数据
	 * @param sendTime
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据sendTime更新数据", description = "根据条件更新数据")
	@PutMapping("/update/sendTime/{sendTime}")
	Result updateBySendTime(@PathVariable("sendTime") Long sendTime, @RequestBody ChatMessagePojo np) {
		Query<ChatMessagePojo> query = new Query<>();
		
		ChatMessagePojo chatMessagePojo = new ChatMessagePojo();
		
		chatMessagePojo.setSendTime(sendTime);
		
		query.setPojo(chatMessagePojo);
		
		chatMessageService.update(query, np);
		
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
	Result updateByContactId(@PathVariable("contactId") String contactId, @RequestBody ChatMessagePojo np) {
		Query<ChatMessagePojo> query = new Query<>();
		
		ChatMessagePojo chatMessagePojo = new ChatMessagePojo();
		
		chatMessagePojo.setContactId(contactId);
		
		query.setPojo(chatMessagePojo);
		
		chatMessageService.update(query, np);
		
		return Result.success();
	}


}