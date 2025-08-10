package com.qinge.backend.service.Impl;

import com.qinge.backend.entity.common.FileObj;
import com.qinge.backend.entity.constants.ClassDir;
import com.qinge.backend.response.Result;
import com.qinge.backend.service.TemplateTypeService;
import com.qinge.backend.utils.FileTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @Data: 2025/8/9 16:06
 * @Author: Lc
 * @Description:
 */


@Slf4j
@Service
public class TemplateTypeServiceImpl implements TemplateTypeService {

    /**
     * 获取模板类型 及 子目录(模板) 列表
     * @return 模板类型列表
     */
    @Override
    public List<FileObj> getTemplateList() {
        File templateDir = new File(ClassDir.TEMPLATE_DIR);
        if (!templateDir.exists() || !templateDir.isDirectory()) {
            log.error("模板目录不存在");
            throw new RuntimeException("模板目录不存在");
        }

        File[] typeDirs = templateDir.listFiles();
        if (typeDirs == null || typeDirs.length == 0) {
            log.error("模板目录下没有模板类型");
            throw new RuntimeException("模板目录下没有模板类型");
        }

        return FileTools.readDir(templateDir);
    }

    /**
     * 添加模板类型
     * @param type 模板类型
     */
    @Override
    public void addTemplateType(String type) {
        // 检查模板类型是否存在
        File typeDir = new File(ClassDir.TEMPLATE_DIR + File.separator + type);
        if (typeDir.exists()) {
            log.error("模板类型已存在,{}", type);
            throw new RuntimeException("模板类型已存在");
        }

        // 创建模板类型目录
        if (!typeDir.mkdirs()) {
            log.error("创建模板类型目录失败,{}", type);
            throw new RuntimeException("创建模板类型目录失败");
        }
    }

    /**
     * 删除模板类型
     * @param type 模板类型
     */
    @Override
    public void deleteTemplateType(String type) {
        // 检查模板类型是否存在
        File typeDir = new File(ClassDir.TEMPLATE_DIR + File.separator + type);
        if (!typeDir.exists() || !typeDir.isDirectory()) {
            log.error("模板类型不存在,{}", type);
            throw new RuntimeException("模板类型不存在");
        }

        FileTools.deleteDirectory(typeDir);

        if (typeDir.exists()) {
            log.error("删除模板类型失败,{}", type);
            throw new RuntimeException("删除模板类型失败");
        }
    }

    /**
     * 更新模板类型
     * @param type 模板类型
     * @param newType 新的模板类型
     */
    @Override
    public void updateTemplateType(String type, String newType) {
        // 检查模板类型是否存在
        File typeDir = new File(ClassDir.TEMPLATE_DIR + File.separator + type);
        if (!typeDir.exists() || !typeDir.isDirectory()) {
            log.error("模板类型不存在,{}", type);
            throw new RuntimeException("模板类型不存在");
        }

        // 检查新模板类型是否存在
        File newTypeDir = new File(ClassDir.TEMPLATE_DIR + File.separator + newType);
        if (newTypeDir.exists()) {
            log.error("新的模板类型已存在,{}", newType);
            throw new RuntimeException("新的模板类型已存在");
        }


        boolean isSuccess = FileTools.renameFolder(typeDir.getPath(), newTypeDir.getPath());

        if (!isSuccess) {
            log.error("更新模板类型失败,{}", type);
            throw new RuntimeException("更新模板类型失败");
        }
    }
}
