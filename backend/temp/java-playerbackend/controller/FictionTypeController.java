package com.qinge.playerbackend.controller;
import com.qinge.playerbackend.entity.pojo.FictionTypePojo;
import com.qinge.playerbackend.service.FictionTypeService;
import com.qinge.playerbackend.entity.vo.Result;
import com.qinge.playerbackend.entity.query.Query;

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
* @Data: 2025/06/11 00:11
* @Author: Lc
* @Description:
*/


@RestController
@RequestMapping(value = "/fictionType")
@Tag(name = "FictionType接口", description = "")
public class FictionTypeController  {
	@Resource
	private FictionTypeService<FictionTypePojo> fictionTypeService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<FictionTypePojo> query = new Query<>();
		
		List<FictionTypePojo> FictionTypeList = fictionTypeService.select(query);
		
		return Result.success(FictionTypeList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody FictionTypePojo pojo) {
		Query<FictionTypePojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		fictionTypeService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody FictionTypePojo pojo) {
		Query<FictionTypePojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		fictionTypeService.delete(query);
		
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
	Result updateById(@PathVariable("id") Long id, @RequestBody FictionTypePojo np) {
		Query<FictionTypePojo> query = new Query<>();
		
		FictionTypePojo fictionTypePojo = new FictionTypePojo();
		
		fictionTypePojo.setId(id);
		
		query.setPojo(fictionTypePojo);
		
		fictionTypeService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据type更新数据
	 * @param type
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据type更新数据", description = "根据条件更新数据")
	@PutMapping("/update/type/{type}")
	Result updateByType(@PathVariable("type") String type, @RequestBody FictionTypePojo np) {
		Query<FictionTypePojo> query = new Query<>();
		
		FictionTypePojo fictionTypePojo = new FictionTypePojo();
		
		fictionTypePojo.setType(type);
		
		query.setPojo(fictionTypePojo);
		
		fictionTypeService.update(query, np);
		
		return Result.success();
	}


}