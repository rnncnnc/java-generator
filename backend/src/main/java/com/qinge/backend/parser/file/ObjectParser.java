package com.qinge.backend.parser.file;

import com.qinge.backend.entity.template.Template;
import com.qinge.backend.entity.template.object.FileObject;
import com.qinge.backend.utils.FileTools;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @Data: 2025/6/5 11:14
 * @Author: Lc
 * @Description:
 */


public abstract class ObjectParser implements FileParser {

    /**
     * 从外置.yml文件路径解析为JavaClass
     * @param ymlFilePath 外置.yml文件路径（如 "classpath:templates/controller.yml"）
     * @return JavaClass 对象
     */
    public Template parseFromYml(String ymlFilePath) throws IOException {
        // 1. 读取 YAML 文件
        try (InputStream inputStream = new FileInputStream(ymlFilePath)) {
            if (inputStream == null) {
                throw new IOException("YAML 文件未找到: " + ymlFilePath);
            }
            // 2. 解析 YAML 为 Map（根节点）
            Yaml yaml = new Yaml();
            Map<String, Object> yamlRootMap = yaml.load(inputStream);
            // 3. 提取 classObj 节点（核心数据）
            Map<String, Object> classObjMap = (Map<String, Object>) yamlRootMap.get("templateObj");

            // 4. 调用模板解析方法
            FileObject fileObj = parseTemplate(classObjMap);

            Template fileObjTemplate = new Template();
            fileObjTemplate.setFileType((String) yamlRootMap.get("fileType"));
            fileObjTemplate.setClassType((String) yamlRootMap.get("classType"));
            fileObjTemplate.setTemplateObj(fileObj);

            return fileObjTemplate;
        }
    }

    abstract public FileObject parseTemplate(Map<String, Object> templateMap);
}
