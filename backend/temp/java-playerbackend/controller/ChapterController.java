package com.qinge.playerbackend.controller;
import com.qinge.playerbackend.entity.vo.Result;
import com.qinge.playerbackend.entity.query.Query;
import com.qinge.playerbackend.entity.pojo.ChapterPojo;
import com.qinge.playerbackend.service.ChapterService;

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
@RequestMapping(value = "/chapter")
@Tag(name = "Chapter接口", description = "章节表")
public class ChapterController  {
	@Resource
	private ChapterService<ChapterPojo> chapterService;


	/**
	 * 获取数据
	 * @return
	 */
	@Operation(summary = "获取数据", description = "返回数据")
	@GetMapping("/select")
	public Result select() {
		Query<ChapterPojo> query = new Query<>();
		
		List<ChapterPojo> ChapterList = chapterService.select(query);
		
		return Result.success(ChapterList);
	}


	/**
	 * 插入数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "插入数据", description = "向数据库插入数据")
	@PostMapping("/insert")
	Result insert(@RequestBody ChapterPojo pojo) {
		Query<ChapterPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chapterService.insert(query);
		
		return Result.success();
	}


	/**
	 * 删除数据
	 * @param pojo
	 * @return
	 */
	@Operation(summary = "删除数据", description = "根据条件删除数据")
	@DeleteMapping("/delete")
	Result delete(@RequestBody ChapterPojo pojo) {
		Query<ChapterPojo> query = new Query<>();
		
		query.setPojo(pojo);
		
		chapterService.delete(query);
		
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
	Result updateById(@PathVariable("id") Long id, @RequestBody ChapterPojo np) {
		Query<ChapterPojo> query = new Query<>();
		
		ChapterPojo chapterPojo = new ChapterPojo();
		
		chapterPojo.setId(id);
		
		query.setPojo(chapterPojo);
		
		chapterService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据fictionId更新数据
	 * @param fictionId
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据fictionId更新数据", description = "根据条件更新数据")
	@PutMapping("/update/fictionId/{fictionId}")
	Result updateByFictionId(@PathVariable("fictionId") Long fictionId, @RequestBody ChapterPojo np) {
		Query<ChapterPojo> query = new Query<>();
		
		ChapterPojo chapterPojo = new ChapterPojo();
		
		chapterPojo.setFictionId(fictionId);
		
		query.setPojo(chapterPojo);
		
		chapterService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据typeId更新数据
	 * @param typeId
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据typeId更新数据", description = "根据条件更新数据")
	@PutMapping("/update/typeId/{typeId}")
	Result updateByTypeId(@PathVariable("typeId") Long typeId, @RequestBody ChapterPojo np) {
		Query<ChapterPojo> query = new Query<>();
		
		ChapterPojo chapterPojo = new ChapterPojo();
		
		chapterPojo.setTypeId(typeId);
		
		query.setPojo(chapterPojo);
		
		chapterService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据title更新数据
	 * @param title
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据title更新数据", description = "根据条件更新数据")
	@PutMapping("/update/title/{title}")
	Result updateByTitle(@PathVariable("title") String title, @RequestBody ChapterPojo np) {
		Query<ChapterPojo> query = new Query<>();
		
		ChapterPojo chapterPojo = new ChapterPojo();
		
		chapterPojo.setTitle(title);
		
		query.setPojo(chapterPojo);
		
		chapterService.update(query, np);
		
		return Result.success();
	}


	/**
	 * 根据url更新数据
	 * @param url
	 * @param np
	 * @return
	 */
	@Operation(summary = "根据url更新数据", description = "根据条件更新数据")
	@PutMapping("/update/url/{url}")
	Result updateByUrl(@PathVariable("url") String url, @RequestBody ChapterPojo np) {
		Query<ChapterPojo> query = new Query<>();
		
		ChapterPojo chapterPojo = new ChapterPojo();
		
		chapterPojo.setUrl(url);
		
		query.setPojo(chapterPojo);
		
		chapterService.update(query, np);
		
		return Result.success();
	}


}