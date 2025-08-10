package com.qinge.backend.controller;

import com.qinge.backend.dto.Template;
import com.qinge.backend.entity.common.FileObj;
import com.qinge.backend.response.Result;
import com.qinge.backend.service.TemplateService;
import com.qinge.backend.service.TemplateTypeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Data: 2025/8/8 20:07
 * @Author: Lc
 * @Description:
 */


@Slf4j
@RestController
@RequestMapping("/template")
public class TemplateController {

    @Resource
    private TemplateTypeService templateTypeService;

    @Resource
    private TemplateService templateService;

    /**
     * 获取模板类型 及 子目录(模板) 列表
     *
     * @return 模板类型列表
     */
    @Operation(summary = "获取模板类型列表", description = "获取模板类型列表")
    @GetMapping("/getTemplateList")
    public Result getTemplateList() {

        List<FileObj> fileObjList = templateTypeService.getTemplateList();

        return Result.success(fileObjList);
    }


    /**
     * 添加模板类型
     *
     * @param type 模板类型
     * @return 结果
     */
    @Operation(summary = "添加模板类型", description = "添加模板类型")
    @PostMapping("/addTemplateType/{type}")
    public Result addTemplateType(@PathVariable("type") String type) {

        templateTypeService.addTemplateType(type);

        return Result.success("添加模板类型成功");
    }

    /**
     * 删除模板类型
     *
     * @param type 模板类型
     * @return 结果
     */
    @Operation(summary = "删除模板类型", description = "删除模板类型")
    @DeleteMapping("/deleteTemplateType/{type}")
    public Result deleteTemplateType(@PathVariable("type") String type) {

        templateTypeService.deleteTemplateType(type);

        return Result.success("删除模板类型成功");
    }

    /**
     * 更新模板类型
     *
     * @param type    模板类型
     * @param newType 新的模板类型
     * @return 结果
     */
    @Operation(summary = "更新模板类型", description = "更新模板类型")
    @PutMapping("/updateTemplateType/{type}/{newType}")
    public Result updateTemplateType(@PathVariable("type") String type, @PathVariable("newType") String newType) {

        templateTypeService.updateTemplateType(type, newType);

        return Result.success("更新模板类型成功");
    }


    /**
     * 获取指定路径的模板内容
     *
     * @param type 模板类型
     * @param file 模板文件名
     * @return 模板内容
     */
    @Operation(summary = "获取指定路径的模板内容", description = "获取指定路径的模板内容")
    @GetMapping("/getTemplate")
    public Result getTemplate(@RequestParam("type") String type, @RequestParam("file") String file) {

        Map<String, Object> template = templateService.getTemplate(type, file);

        return Result.success(template);
    }

    /**
     * 添加模板文件
     *
     * @param template 模板信息
     * @return 结果
     */
    @Operation(summary = "添加模板文件", description = "添加模板文件")
    @PostMapping("/addTemplateFile")
    public Result addTemplateFile(@RequestBody Template template) {

        templateService.addTemplateFile(template);

        return Result.success("添加模板文件成功");
    }



    /**
     * 删除模板文件
     * @param type 模板类型
     * @param file 模板文件名
     * @return 结果
     */
    @Operation(summary = "删除模板文件", description = "删除模板文件")
    @DeleteMapping("/deleteTemplateFile/{type}/{file}")
    public Result deleteTemplateFile(@PathVariable("type") String type, @PathVariable("file") String file) {

        templateService.deleteTemplateFile(type, file);

        return Result.success("删除模板文件成功");
    }

    /**
     * 更新模板文件
     * @param type 模板类型
     * @param file 模板文件名
     * @param template 模板信息
     * @return 结果
     */
    @Operation(summary = "更新模板文件", description = "更新模板文件")
    @PutMapping("/updateTemplateFile/{type}/{file}")
    public Result updateTemplateFile(@PathVariable("type") String type, @PathVariable("file") String file, @RequestBody Template template) {

        templateService.updateTemplateFile(type, file, template);

        return Result.success("更新模板文件成功");
    }


    /**
     * 重置模板
     * @return 结果
     */
    @Operation(summary = "重置模板", description = "重置模板")
    @GetMapping("/resetTemplate")
    public Result resetTemplate() {
        templateService.resetTemplate();
        return Result.success("重置模板成功");
    }
}
