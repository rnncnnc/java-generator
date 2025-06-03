package com.qinge.backend.factory;

import com.qinge.backend.builder.Builder;
import com.qinge.backend.builder.FileBuilder;
import com.qinge.backend.entity.constants.Constant;
import com.qinge.backend.utils.StringTools;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Data: 2025/6/1 16:40
 * @Author: Lc
 * @Description:
 */


public class BuilderFactory {

    public static FileBuilder getBuilder(String type) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        String name = Builder.class.getName();

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

            return (FileBuilder) declaredConstructor.newInstance();
        } else {
            // TODO 异常 全局异常处理器处理    模板不在可处理范围内
            return null;
        }
    }
}
