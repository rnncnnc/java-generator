package com.qinge.backend.controller;
import com.qinge.backend.service.AppUpdateService;
import org.springframework.web.bind.annotation.PostMapping;
import com.qinge.backend.entity.pojo.AppUpdatePojo;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PutMapping;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.qinge.backend.controller.entity.vo.Result;
import com.qinge.backend.controller.entity.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



@Tag(name = "测试接口", description = "测试接口")
@RestController
public class AppUpdateController {

    @Resource
    private AppUpdateService<AppUpdatePojo> appUpdateService;


    /**
     * 获取数据
     * @return
     */
    @Operation(summary = "获取数据", description = "返回数据")
    @GetMapping("/select")
    public Result select() {
        Query<AppUpdatePojo> query = new Query<>();

        List<AppUpdatePojo> AppUpdateList = appUpdateService.select(query);

        return Result.success(AppUpdateList);

    }

    /**
     * 插入数据
     * @param pojo
     * @return
     */
    @Operation(summary = "插入数据", description = "向数据库插入数据")
    @PostMapping("/insert")
    public Result insert(@RequestBody AppUpdatePojo pojo) {
        Query<AppUpdatePojo> query = new Query<>();

        query.setPojo(pojo);

        appUpdateService.insert(query);

        return Result.success();

    }

    /**
     * 删除数据
     * @param pojo
     * @return
     */
    @Operation(summary = "删除数据", description = "根据条件删除数据")
    @DeleteMapping("/delete")
    public Result delete(@RequestBody AppUpdatePojo pojo) {
        Query<FictionPojo> query = new Query<>();

        query.setPojo(pojo);

        appUpdateService.delete(query);

        return Result.success();

    }


}