package com.qinge.backend.controller;
import com.qinge.backend.entity.query.Query;
import com.qinge.backend.entity.pojo.HistoryPojo;
import com.qinge.backend.entity.vo.Result;
import com.qinge.backend.service.HistoryService;

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


/**
* @Data: 2025/06/11 20:57
* @Author: Lc
* @Description:
*/


@RestController
@RequestMapping(value = "/history")
@Tag(name = "History接口", description = "历史")
public class HistoryController  {
	@Resource
	private HistoryService<HistoryPojo> historyService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<HistoryPojo> query = new Query<>();
		
		List<HistoryPojo> HistoryList = historyService.select(query);
		
		return Result.success(HistoryList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody HistoryPojo pojo) {
		Query<HistoryPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		historyService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody HistoryPojo pojo) {
		Query<HistoryPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		historyService.delete(query);
		
		return Result.success();
	}


	/**
	 * 根据id更新数据
	 * @param id
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据id更新数据", description = "根据条件更新数据")
	@PutMapping("/update/id/{id}")
	Result updateById(@PathVariable("id") Integer id, @RequestBody HistoryPojo np) {
		Query<HistoryPojo> query = new Query<>();
		
		HistoryPojo historyPojo = new HistoryPojo();
		
		historyPojo.setId(id);
		
		query.setPojo(historyPojo);
		
		historyService.update(query, np);
		
		return Result.success();
	}


}