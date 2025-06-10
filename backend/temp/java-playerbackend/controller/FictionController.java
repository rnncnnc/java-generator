package com.qinge.playerbackend.controller;
import com.qinge.playerbackend.entity.pojo.FictionPojo;
import com.qinge.playerbackend.entity.vo.Result;
import com.qinge.playerbackend.service.FictionService;
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
@RequestMapping(value = "/fiction")
@Tag(name = "Fiction接口", description = "")
public class FictionController  {
	@Resource
	private FictionService<FictionPojo> fictionService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<FictionPojo> query = new Query<>();
		
		List<FictionPojo> FictionList = fictionService.select(query);
		
		return Result.success(FictionList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody FictionPojo pojo) {
		Query<FictionPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		fictionService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody FictionPojo pojo) {
		Query<FictionPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		fictionService.delete(query);
		
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
	Result updateById(@PathVariable("id") Long id, @RequestBody FictionPojo np) {
		Query<FictionPojo> query = new Query<>();
		
		FictionPojo fictionPojo = new FictionPojo();
		
		fictionPojo.setId(id);
		
		query.setPojo(fictionPojo);
		
		fictionService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据novel更新数据
	 * @param novel
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据novel更新数据", description = "根据条件更新数据")
	@PutMapping("/update/novel/{novel}")
	Result updateByNovel(@PathVariable("novel") String novel, @RequestBody FictionPojo np) {
		Query<FictionPojo> query = new Query<>();
		
		FictionPojo fictionPojo = new FictionPojo();
		
		fictionPojo.setNovel(novel);
		
		query.setPojo(fictionPojo);
		
		fictionService.update(query, np);
		
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
	Result updateByType(@PathVariable("type") String type, @RequestBody FictionPojo np) {
		Query<FictionPojo> query = new Query<>();
		
		FictionPojo fictionPojo = new FictionPojo();
		
		fictionPojo.setType(type);
		
		query.setPojo(fictionPojo);
		
		fictionService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据chapCount更新数据
	 * @param chapCount
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据chapCount更新数据", description = "根据条件更新数据")
	@PutMapping("/update/chapCount/{chapCount}")
	Result updateByChapCount(@PathVariable("chapCount") String chapCount, @RequestBody FictionPojo np) {
		Query<FictionPojo> query = new Query<>();
		
		FictionPojo fictionPojo = new FictionPojo();
		
		fictionPojo.setChapCount(chapCount);
		
		query.setPojo(fictionPojo);
		
		fictionService.update(query, np);
		
		return Result.success();
	}


}