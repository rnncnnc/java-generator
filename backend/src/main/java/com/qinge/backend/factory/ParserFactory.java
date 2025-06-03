package com.qinge.backend.factory;

import com.qinge.backend.builder.Builder;
import com.qinge.backend.builder.FileBuilder;
import com.qinge.backend.entity.constants.Constant;
import com.qinge.backend.parser.Parser;
import com.qinge.backend.utils.StringTools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @Data: 2025/6/1 17:27
 * @Author: Lc
 * @Description:
 */


public class ParserFactory {

    public static Parser buildParser(String type) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String name = Parser.class.getName();

        String pre = name.substring(0, name.lastIndexOf("."));

        String sub = name.substring(name.lastIndexOf(".") + 1);

        List<String> fileType = List.of(Constant.FILE_TYPE);
        if (fileType.contains(type.toLowerCase())) {
            sub = StringTools.firstToUppercase(type) + sub;

            String className = pre + "." + sub;

            // 获取类的class对象
            Class<?> clazz = Class.forName(className);

            // 获取类的构造器
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();

            return (Parser) declaredConstructor.newInstance();
        } else {
            // TODO 异常 全局异常处理器处理    模板不在可处理范围内
            return null;
        }
    }
}
