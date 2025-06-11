package com.qinge.bakcend.controller;
import com.qinge.bakcend.service.GroupInfoService;
import com.qinge.bakcend.entity.query.Query;
import com.qinge.bakcend.entity.pojo.GroupInfoPojo;
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
@RequestMapping(value = "/groupInfo")
@Tag(name = "GroupInfo接口", description = "")
public class GroupInfoController  {
	@Resource
	private GroupInfoService<GroupInfoPojo> groupInfoService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<GroupInfoPojo> query = new Query<>();
		
		List<GroupInfoPojo> GroupInfoList = groupInfoService.select(query);
		
		return Result.success(GroupInfoList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody GroupInfoPojo pojo) {
		Query<GroupInfoPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		groupInfoService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody GroupInfoPojo pojo) {
		Query<GroupInfoPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		groupInfoService.delete(query);
		
		return Result.success();
	}


	/**
	 * 根据groupId更新数据
	 * @param groupId
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据groupId更新数据", description = "根据条件更新数据")
	@PutMapping("/update/groupId/{groupId}")
	Result updateByGroupId(@PathVariable("groupId") String groupId, @RequestBody GroupInfoPojo np) {
		Query<GroupInfoPojo> query = new Query<>();
		
		GroupInfoPojo groupInfoPojo = new GroupInfoPojo();
		
		groupInfoPojo.setGroupId(groupId);
		
		query.setPojo(groupInfoPojo);
		
		groupInfoService.update(query, np);
		
		return Result.success();
	}


}