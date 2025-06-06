package com.qinge.backend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Data: 2025/6/6 11:59
 * @Author: Lc
 * @Description:
 */


public class JsonTools {

    // 单例ObjectMapper（线程安全）
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 对象转JSON字符串
     * @param obj 待序列化的对象
     * @return JSON字符串
     */
    public static String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * JSON字符串转对象
     * @param json JSON字符串
     * @param clazz 目标对象的Class类型
     * @param <T> 泛型类型
     * @return 反序列化后的对象
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }
}
