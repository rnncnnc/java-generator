package com.qinge.backend.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qinge.backend.entity.common.CommonVariables;
import com.qinge.backend.entity.template.object.FileObject;
import com.qinge.backend.entity.enums.KeywordMethods;
import com.qinge.backend.utils.JsonTools;
import com.qinge.backend.utils.StringTools;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Data: 2025/6/1 14:17
 * @Author: Lc
 * @Description:
 */


@Slf4j
public abstract class FileBuilder implements Builder {

    // 基础包名
    protected String basePackage;

    // 临时目录
    protected String temPath;

    // 公共变量
    protected CommonVariables commonVariables;

    /**
     * 替换关键字
     * @param obj
     * @return
     */
    protected <T, E> T replaceKeyword(T obj, E source) throws JsonProcessingException {
        String json = JsonTools.toJson(obj);

        Set<String> keys = StringTools.extractKeys(json);

        Map<String, String> keywrodMap = new HashMap<>();

        String sourceType = source.getClass().getSimpleName();

        // 将关键字替换为对应的值
        for (String key : keys) {
            if (!key.contains(sourceType) && !sourceType.equals("CommonVariables")) {
                continue;
            }

            java.lang.reflect.Method method = KeywordMethods.getByName(key);
            if (method != null) {
                try {
                    System.out.println(source);
                    String value = ((String) method.invoke(source)).trim();
                    keywrodMap.put(key, value);
                } catch (InvocationTargetException | IllegalAccessException | NullPointerException e) {
                    log.error("替换关键字失败: {}", key);
                    throw new RuntimeException(e);
                }
            }
        }

        // 替换关键字
        for (Map.Entry<String, String> entry : keywrodMap.entrySet()) {
            json = json.replace("$(" + entry.getKey() + ")", entry.getValue());
        }

        return (T) JsonTools.fromJson(json, obj.getClass());
    }

    /**
     * 构建文件
     */
    @Override
    abstract public String build(FileObject fileObj) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    public FileBuilder() {
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getTemPath() {
        return temPath;
    }

    public void setTemPath(String temPath) {
        this.temPath = temPath;
    }

    public CommonVariables getCommonVariables() {
        return commonVariables;
    }

    public void setCommonVariables(CommonVariables commonVariables) {
        this.commonVariables = commonVariables;
    }
}
