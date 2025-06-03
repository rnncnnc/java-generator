package com.qinge.backend.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qinge.backend.entity.constants.MySqlConstant;
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

    /**
     * 将mysql的数据类型转为java数据类型，并添加全类名
     * @param dataType
     * @param importList
     * @return
     */
    public static String dataTypeToJava(String dataType, Set<String> importList) {
        String newDateType = "";

        // 将mysql的数据类型转为java数据类型，并添加全类名
        if (StringTools.contains(MySqlConstant.SQL_DATE_TYPE, dataType)) {
            newDateType = "Date";
            importList.add(Date.class.getName());
            importList.add(JsonFormat.class.getName());
            importList.add(DateTimeFormat.class.getName());
        } else if (StringTools.contains(MySqlConstant.SQL_DATE_TIME_TYPE, dataType)) {
            newDateType = "Long";
            importList.add(Long.class.getName());
        } else if (StringTools.contains(MySqlConstant.SQL_DECIMAL_TYPE, dataType)) {
            newDateType = "BigDecimal";
            importList.add(BigDecimal.class.getName());
        } else if (StringTools.contains(MySqlConstant.SQL_STRING_TYPES, dataType)) {
            newDateType = "String";
            importList.add(String.class.getName());
        } else if (StringTools.contains(MySqlConstant.SQL_INTEGER_TYPES, dataType)) {
            newDateType = "Integer";
            importList.add(Integer.class.getName());
        } else if (StringTools.contains(MySqlConstant.SQL_LONG_TYPES, dataType)) {
            newDateType = "Long";
            importList.add(Long.class.getName());
        }

        return newDateType;
    }
}
