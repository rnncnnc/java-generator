package com.qinge.playerbackend.controller;
import com.qinge.playerbackend.entity.vo.Result;
import com.qinge.playerbackend.entity.query.Query;
import com.qinge.playerbackend.service.UserContactApplyService;
import com.qinge.playerbackend.entity.pojo.UserContactApplyPojo;

import java.lang.Long;
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
import java.lang.String;


/**
* @Data: 2025/06/10 18:54
* @Author: Lc
* @Description:
*/


@RestController
@RequestMapping(value = "/userContactApply")
@Tag(name = "UserContactApply接口", description = "联系人申请")
public class UserContactApplyController  {
	@Resource
	private UserContactApplyService<UserContactApplyPojo> userContactApplyService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<UserContactApplyPojo> query = new Query<>();
		
		List<UserContactApplyPojo> UserContactApplyList = userContactApplyService.select(query);
		
		return Result.success(UserContactApplyList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody UserContactApplyPojo pojo) {
		Query<UserContactApplyPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		userContactApplyService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody UserContactApplyPojo pojo) {
		Query<UserContactApplyPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		userContactApplyService.delete(query);
		
		return Result.success();
	}


	/**
	 * 根据applyId更新数据
	 * @param applyId
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据applyId更新数据", description = "根据条件更新数据")
	@PutMapping("/update/applyId/{applyId}")
	Result updateByApplyId(@PathVariable("applyId") Integer applyId, @RequestBody UserContactApplyPojo np) {
		Query<UserContactApplyPojo> query = new Query<>();
		
		UserContactApplyPojo userContactApplyPojo = new UserContactApplyPojo();
		
		userContactApplyPojo.setApplyId(applyId);
		
		query.setPojo(userContactApplyPojo);
		
		userContactApplyService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据applyUserId更新数据
	 * @param applyUserId
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据applyUserId更新数据", description = "根据条件更新数据")
	@PutMapping("/update/applyUserId/{applyUserId}")
	Result updateByApplyUserId(@PathVariable("applyUserId") String applyUserId, @RequestBody UserContactApplyPojo np) {
		Query<UserContactApplyPojo> query = new Query<>();
		
		UserContactApplyPojo userContactApplyPojo = new UserContactApplyPojo();
		
		userContactApplyPojo.setApplyUserId(applyUserId);
		
		query.setPojo(userContactApplyPojo);
		
		userContactApplyService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据lastApplyTime更新数据
	 * @param lastApplyTime
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据lastApplyTime更新数据", description = "根据条件更新数据")
	@PutMapping("/update/lastApplyTime/{lastApplyTime}")
	Result updateByLastApplyTime(@PathVariable("lastApplyTime") Long lastApplyTime, @RequestBody UserContactApplyPojo np) {
		Query<UserContactApplyPojo> query = new Query<>();
		
		UserContactApplyPojo userContactApplyPojo = new UserContactApplyPojo();
		
		userContactApplyPojo.setLastApplyTime(lastApplyTime);
		
		query.setPojo(userContactApplyPojo);
		
		userContactApplyService.update(query, np);
		
		return Result.success();
	}


}