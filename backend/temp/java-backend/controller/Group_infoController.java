package com.qinge.backend.controller;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.entity.pojo.Group_infoPojo;
import com.qinge.backend.entity.vo.Result;
import com.qinge.backend.service.Group_infoService;

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
@RequestMapping(value = "/group_info")
@Tag(name = "Group_info接口", description = "")
public class Group_infoController  {
	@Resource
	private Group_infoService<Group_infoPojo> group_infoService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<Group_infoPojo> query = new Query<>();
		
		List<Group_infoPojo> Group_infoList = group_infoService.select(query);
		
		return Result.success(Group_infoList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody Group_infoPojo pojo) {
		Query<Group_infoPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		group_infoService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody Group_infoPojo pojo) {
		Query<Group_infoPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		group_infoService.delete(query);
		
		return Result.success();
	}


	/**
	 * 根据group_id更新数据
	 * @param group_id
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据group_id更新数据", description = "根据条件更新数据")
	@PutMapping("/update/group_id/{group_id}")
	Result updateByGroup_id(@PathVariable("group_id") String group_id, @RequestBody Group_infoPojo np) {
		Query<Group_infoPojo> query = new Query<>();
		
		Group_infoPojo group_infoPojo = new Group_infoPojo();
		
		group_infoPojo.setGroup_id(group_id);
		
		query.setPojo(group_infoPojo);
		
		group_infoService.update(query, np);
		
		return Result.success();
	}


}