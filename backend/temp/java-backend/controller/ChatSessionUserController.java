package com.qinge.backend.controller;
import org.springframework.web.bind.annotation.PostMapping;
import com.qinge.backend.service.ChatSessionUserService;
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
import com.qinge.backend.entity.pojo.ChatSessionUserPojo;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



@Tag(name = "测试接口", description = "测试接口")
@RestController
public class ChatSessionUserController {

    @Resource
    private ChatSessionUserService<ChatSessionUserPojo> chatSessionUserService;


    /**
     * 获取数据
     * @return
     */
    @Operation(summary = "获取数据", description = "返回数据")
    @GetMapping("/select")
    public Result select() {
        Query<ChatSessionUserPojo> query = new Query<>();

        List<ChatSessionUserPojo> ChatSessionUserList = chatSessionUserService.select(query);

        return Result.success(ChatSessionUserList);

    }

    /**
     * 插入数据
     * @param pojo
     * @return
     */
    @Operation(summary = "插入数据", description = "向数据库插入数据")
    @PostMapping("/insert")
    public Result insert(@RequestBody ChatSessionUserPojo pojo) {
        Query<ChatSessionUserPojo> query = new Query<>();

        query.setPojo(pojo);

        chatSessionUserService.insert(query);

        return Result.success();

    }

    /**
     * 删除数据
     * @param pojo
     * @return
     */
    @Operation(summary = "删除数据", description = "根据条件删除数据")
    @DeleteMapping("/delete")
    public Result delete(@RequestBody ChatSessionUserPojo pojo) {
        Query<FictionPojo> query = new Query<>();

        query.setPojo(pojo);

        chatSessionUserService.delete(query);

        return Result.success();

    }


}