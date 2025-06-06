package com.qinge.backend.utils;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Data: 2025/6/5 11:23
 * @Author: Lc
 * @Description:
 */


public class ClassTools {

    /**
     * 深拷贝方法
     * @param source
     * @return
     * @param <T>
     */
    public static  <T extends Serializable> T deepCopy(T source) {
        return SerializationUtils.clone(source);
    }


    /**
     * 通用反射赋值方法（支持查找父类字段）
     * @param instance 目标对象实例
     * @param fieldName 字段名（区分大小写）
     * @param value 要设置的值
     */
    public static void setFieldValue(Object instance, String fieldName, Object value) throws Exception {
        // 递归查找当前类及其父类的字段
        Class<?> clazz = instance.getClass();
        while (clazz != null) {
            try {
                // 尝试获取当前类声明的字段（包括 private）
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true); // 允许访问私有字段
                field.set(instance, value); // 设置字段值
                return; // 找到字段并设置后返回
            } catch (NoSuchFieldException e) {
                // 当前类未找到，继续查找父类
                clazz = clazz.getSuperclass();
            }
        }
        // 所有父类都未找到字段
        throw new NoSuchFieldException("字段 " + fieldName + " 不存在于类 " + instance.getClass().getName() + " 或其父类中");
    }

    /**
     * 调用object里面的方法
     * @param object
     * @param methodName
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object runMethodByMethodName(Object object,String methodName, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<?> aClass = object.getClass();

        Method method = aClass.getMethod(methodName);

        return method.invoke(object, args);
    }


    /**
     * 通过全类名动态构建类实例（关键方法）
     * @param fullClassName 全限定类名（如 "com.qinge.backend.builder.PojoBuilder"）
     * @param <T> 目标类类型
     * @return 类实例
     */
    public static <T> T buildClassByFullName(String fullClassName) {
        try {
            // 1. 加载 Class 对象
            Class<?> clazz = Class.forName(fullClassName);
            // 2. 获取无参构造方法（假设目标类有公开的无参构造）
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            // 3. 实例化对象（设置可访问性，避免私有构造限制）
            constructor.setAccessible(true);
            return (T) constructor.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("类未找到: " + fullClassName, e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("类无无参构造: " + fullClassName, e);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("实例化失败: " + fullClassName, e);
        }
    }

}
