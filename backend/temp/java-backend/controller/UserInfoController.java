package com.qinge.backend.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.Resource;
import com.qinge.backend.service.UserInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PutMapping;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.qinge.backend.controller.entity.vo.Result;
import com.qinge.backend.controller.entity.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import com.qinge.backend.entity.pojo.UserInfoPojo;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



@Tag(name = "测试接口", description = "测试接口")
@RestController
public class UserInfoController {

    @Resource
    private UserInfoService<UserInfoPojo> userInfoService;


    /**
     * 获取数据
     * @return
     */
    @Operation(summary = "获取数据", description = "返回数据")
    @GetMapping("/select")
    public Result select() {
        Query<UserInfoPojo> query = new Query<>();

        List<UserInfoPojo> UserInfoList = userInfoService.select(query);

        return Result.success(UserInfoList);

    }

    /**
     * 插入数据
     * @param pojo
     * @return
     */
    @Operation(summary = "插入数据", description = "向数据库插入数据")
    @PostMapping("/insert")
    public Result insert(@RequestBody UserInfoPojo pojo) {
        Query<UserInfoPojo> query = new Query<>();

        query.setPojo(pojo);

        userInfoService.insert(query);

        return Result.success();

    }

    /**
     * 删除数据
     * @param pojo
     * @return
     */
    @Operation(summary = "删除数据", description = "根据条件删除数据")
    @DeleteMapping("/delete")
    public Result delete(@RequestBody UserInfoPojo pojo) {
        Query<FictionPojo> query = new Query<>();

        query.setPojo(pojo);

        userInfoService.delete(query);

        return Result.success();

    }


}