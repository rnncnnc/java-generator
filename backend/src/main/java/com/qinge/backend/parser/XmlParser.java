package com.qinge.backend.parser;

import com.qinge.backend.entity.dto.template.object.FileObject;
import com.qinge.backend.entity.dto.template.object.xml.Tag;
import com.qinge.backend.entity.dto.template.object.xml.TagParam;
import com.qinge.backend.entity.dto.template.object.xml.XmlFile;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Data: 2025/6/7 0:36
 * @Author: Lc
 * @Description:
 */


public class XmlParser extends ObjectParser {

    /**
     * 解析模板
     * @param templateMap 包含模板数据的Map
     * @return
     */
    @Override
    public FileObject parseTemplate(Map<String, Object> templateMap) {
        XmlFile xmlFile = new XmlFile();

        // 设置基础字段
        xmlFile.setName((String) templateMap.get("name"));
        xmlFile.setFilePath((String) templateMap.get("filePath"));
        xmlFile.setHeader((List<String>) templateMap.get("header"));

        // 解析标签
        List<Map<String, Object>> tags = (List<Map<String, Object>>) templateMap.get("tags");
        if (tags != null) {
            xmlFile.setTags(parseTags(tags));
        }

        return xmlFile;
    }

    /**
     * 解析标签
     * @param tagMap
     * @return
     */
    private Tag parseTag(Map<String, Object> tagMap) {
        Tag tag = new Tag();

        if (CollectionUtils.isEmpty(tagMap)) {
            return tag;
        }

        tag.setName((String) tagMap.get("name"));

        List<Map<String, Object>> params = (List<Map<String, Object>>) tagMap.get("params");
        if (params != null) {
            tag.setParams(parseTagParams(params));
        }

        // 解析子标签
        List<Map<String, Object>> tagsMap = (List<Map<String, Object>>) tagMap.get("tags");
        if (tagsMap != null) {
            tag.setTags(parseTags(tagsMap));
        }

        tag.setComment((List<String>) tagMap.get("comment"));

        return tag;
    }

    /**
     * 解析tagList
     * @param tags
     * @return
     */
    private List<Tag> parseTags(List<Map<String, Object>> tags) {
        List<Tag> tagList = new ArrayList<>();

        if (CollectionUtils.isEmpty(tags)) {
            return tagList;
        }

        for (Map<String, Object> tagMap : tags) {
            Tag tag = parseTag(tagMap);
            tagList.add(tag);
        }

        return tagList;
    }

    /**
     * 解析tagParam 标签参数
     * @param tagParamMap
     * @return
     */
    private TagParam parseTagParam(Map<String, Object> tagParamMap) {
        TagParam tagParam = new TagParam();
        if (CollectionUtils.isEmpty(tagParamMap)) {
            return tagParam;
        }

        tagParam.setKey((String) tagParamMap.get("key"));
        tagParam.setValue((String) tagParamMap.get("value"));

        return tagParam;
    }

    /**
     * 解析tagParamList 标签参数列表
     * @param tagParamsMap
     * @return
     */
    private List<TagParam> parseTagParams(List<Map<String, Object>> tagParamsMap) {
        List<TagParam> tagParamList = new ArrayList<>();
        if (CollectionUtils.isEmpty(tagParamsMap)) {
            return tagParamList;
        }
        for (Map<String, Object> tagParamMap : tagParamsMap) {
            TagParam tagParam = parseTagParam(tagParamMap);
            tagParamList.add(tagParam);
        }
        return tagParamList;
    }
}
