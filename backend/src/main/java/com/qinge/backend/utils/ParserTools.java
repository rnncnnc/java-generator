package com.qinge.backend.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Data: 2025/6/1 9:49
 * @Author: Lc
 * @Description:
 */


public class ParserTools {

    /**
     * 提取关键词
     * @param str
     * @return
     */
    public static Set<String> extractKeys(String str, String regex) {
        Set<String> result = new HashSet<>();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        // 遍历所有匹配项
        while (matcher.find()) {
            result.add(matcher.group()); // 添加完整匹配的子串（如#abcd）
        }
        return result;
    }
}
