package com.qinge.backend.service.Impl;

import com.qinge.backend.dto.Template;
import com.qinge.backend.entity.constants.ClassDir;
import com.qinge.backend.response.Result;
import com.qinge.backend.service.TemplateService;
import com.qinge.backend.utils.FileTools;
import com.qinge.backend.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @Data: 2025/8/9 16:12
 * @Author: Lc
 * @Description:
 */


@Slf4j
@Service
public class TemplateServiceImpl implements TemplateService {

    /**
     * 获取模板
     * @param type 模板类型
     * @param file 模板文件
     * @return
     */
    @Override
    public Map<String, Object> getTemplate(String type, String file) {
        // 检查模板类型是否存在
        File typeDir = new File(ClassDir.TEMPLATE_DIR + File.separator + type);
        if (!typeDir.exists()) {
            log.error("模板类型不存在,{}", type);
            throw new RuntimeException("模板类型不存在");
        }

        // 检查模板文件是否存在
        File templateFile = new File(typeDir, file);
        if (!templateFile.exists()) {
            log.error("模板文件不存在,{}/{}", type, file);
            throw new RuntimeException("模板文件不存在");
        }

        // 读取模板文件内容
        try (InputStream inputStream = new java.io.FileInputStream(templateFile)) {

            Yaml yaml = new Yaml();

            log.info("读取模板文件内容,{}/{}", type, file);
            return yaml.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加模板文件
     * @param template 模板文件
     */
    @Override
    public void addTemplateFile(Template template) {

        // 检查模板类型是否存在
        File typeDir = new File(ClassDir.TEMPLATE_DIR + File.separator + template.getType());
        if (!typeDir.exists()) {
            log.error("模板类型不存在,{}", template.getType());
            throw new RuntimeException("模板类型不存在");
        }

        // 检查模板文件是否存在
        File templateFile = new File(typeDir, template.getFile());
        if (templateFile.exists()) {
            log.error("模板文件已存在,{}/{}", template.getType(), template.getFile());
            throw new RuntimeException("模板文件已存在");
        }

        // 创建模板文件
        if (!FileTools.createFileAndSetContent(templateFile, template.getContent())) {
            log.error("创建模板文件失败,{}/{}", template.getType(), template.getFile());
            throw new RuntimeException("创建模板文件失败");
        }
    }

    @Override
    public void deleteTemplateFile(String type, String file) {
        // 检查模板类型是否存在
        File typeDir = new File(ClassDir.TEMPLATE_DIR + File.separator + type);
        if (!typeDir.exists()) {
            log.error("模板类型不存在,{}", type);
            throw new RuntimeException("模板类型不存在");
        }

        // 检查模板文件是否存在
        File templateFile = new File(typeDir, file);
        if (!templateFile.exists()) {
            log.error("模板文件不存在,{}/{}", type, file);
            throw new RuntimeException("模板文件不存在");
        }

        // 删除模板文件
        if (!templateFile.delete()) {
            log.error("删除模板文件失败,{}/{}", type, file);
            throw new RuntimeException("删除模板文件失败");
        }
    }

    @Override
    public void updateTemplateFile(String type, String file, Template template) {
        // 检查模板类型是否存在
        File typeDir = new File(ClassDir.TEMPLATE_DIR + File.separator + type);
        if (!typeDir.exists()) {
            log.error("模板类型不存在,{}", type);
            throw new RuntimeException("模板类型不存在");
        }

        // 检查模板文件是否存在
        File templateFile = new File(typeDir, file);
        if (!templateFile.exists()) {
            log.error("模板文件不存在,{}/{}", type, file);
            throw new RuntimeException("模板文件不存在");
        }

        if (!StringTools.isEmpty(template.getContent())) {
            // 更新模板文件内容
            if (!FileTools.createFileAndSetContent(templateFile, template.getContent())) {
                log.error("更新模板文件内容失败,{}/{}", type, file);
                throw new RuntimeException("更新模板文件内容失败");
            }
        }

        if (!StringTools.isEmpty(template.getFile())) {

            File newFile = new File(typeDir, template.getFile());

            // 检查新文件是否已存在（避免覆盖）
            if (newFile.exists()) {
                log.error("新文件已存在,{}/{}", type, template.getFile());
                throw new IllegalArgumentException("新文件已存在，避免覆盖: " + newFile.getName());
            }

            try (InputStream inputStream = new FileInputStream(templateFile)) {
                byte[] bytes = inputStream.readAllBytes();

                System.out.println(new String(bytes));

                if (!FileTools.createFileAndSetContent(newFile, new String(bytes))) {
                    log.error("更新模板文件内容失败,{}/{}", type, template.getFile());
                    throw new RuntimeException("更新模板文件内容失败");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                // 删除旧文件
                if (!templateFile.delete()) {
                    log.error("删除旧文件失败,{}/{}", type, file);
                    throw new RuntimeException("删除旧文件失败: " + templateFile);
                }
            }

            templateFile = newFile;
        }

        if (!StringTools.isEmpty(template.getType())) {
            // 更新模板文件类型
            if (!FileTools.moveFile(templateFile.getPath(), ClassDir.TEMPLATE_DIR + File.separator + template.getType())) {
                log.error("更新模板文件类型失败,{}/{}", type, file);
                throw new RuntimeException("更新模板文件类型失败");
            }
        }
    }

    /**
     * 重置模板
     */
    @Override
    public void resetTemplate() {
        if (!FileTools.deleteDirectory(new File(ClassDir.TEMPLATE_DIR))) {
            log.error("重置模板失败");
            throw new RuntimeException("重置模板失败");
        }

        if (!FileTools.copyAndRenameFolder(new File(ClassDir.BACKUP_DIR), new File(ClassDir.TEMPLATE_DIR))) {
            log.error("重置模板失败");
            throw new RuntimeException("重置模板失败");
        }
    }
}
