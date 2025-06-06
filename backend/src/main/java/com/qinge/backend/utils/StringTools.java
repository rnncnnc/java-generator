package com.qinge.backend.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Data: 2025/6/1 9:48
 * @Author: Lc
 * @Description:
 */


public class StringTools {

    /**
     * 判断是否为true
     * @param bool
     * @return
     */
    public static Boolean isTrue(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool;
    }

    /**
     * 解析出关键词 $(keyword)
     * @param str
     * @return
     */
    /**
     * 提取关键词
     * @param str
     * @return
     */
    public static Set<String> extractKeys(String str) {
        Set<String> result = new HashSet<>();

        Pattern pattern = Pattern.compile("\\$\\([a-zA-Z]+\\)");
        Matcher matcher = pattern.matcher(str);

        // 遍历所有匹配项
        while (matcher.find()) {
            String keyword = matcher.group();
            result.add(keyword.substring(2, keyword.length() - 1)); // 添加完整匹配的子串（如#abcd）
        }
        return result;
    }

    /**
     * 将字符串转为驼峰
     * @param str
     * @return
     */
    public static String toCamel(String str, String separator) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        String[] split = str.split(separator);

        StringBuilder camelStr = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String part = split[i];
            if (part.isEmpty()) {
                continue; // 跳过空段（如连续下划线）
            }
            // 首字母小写（驼峰）或大写（帕斯卡），根据需求调整
            if (i == 0) {
                camelStr.append(part.toLowerCase()); // 首段保持小写
            } else {
                camelStr.append(firstToUppercase(part.toLowerCase())); // 后续段首字母大写
            }
        }
        return camelStr.toString();
    }

    /**
     * 将字符串首字母变为大写
     * @param str
     * @return
     */
    public static String firstToUppercase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 将字符串首字母变为小写写
     * @param str
     * @return
     */
    public static String firstToLowercase(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 判断字符串数组是否包含指定字符串（严格匹配大小写）
     * @param array 目标数组
     * @param target 要查找的字符串
     * @return 包含返回true，否则false
     */
    public static boolean contains(String[] array, String target) {
        // 使用Stream API遍历数组，检查是否存在匹配项
        return Arrays.stream(array).anyMatch(target::equals);
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }

        return false;
    }
}
