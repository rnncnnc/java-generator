package com.qinge.backend.utils;

import com.qinge.backend.entity.dto.Template;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Data: 2025/5/30 15:25
 * @Author: Lc
 * @Description:
 */


public class Tools {

    /**
     * 递归删除文件夹及内部所有内容
     * @param directory 要删除的文件夹File对象
     * @return 删除成功返回true，失败返回false
     */
    public static boolean deleteDirectory(File directory) {
        // 检查路径是否存在或是否为文件夹
        if (!directory.exists() || !directory.isDirectory()) {
            return false;
        }

        // 递归删除所有子文件和子目录
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 递归删除子目录
                    if (!deleteDirectory(file)) {
                        return false; // 子目录删除失败则整体失败
                    }
                } else {
                    // 删除文件
                    if (!file.delete()) {
                        return false; // 文件删除失败则整体失败
                    }
                }
            }
        }

        // 最后删除空目录
        return directory.delete();
    }


    /**
     * 获取resources下指定文件夹下的所有文件路径
     * @param resourceDir
     * @return
     * @throws IOException
     */
    public static List<String> getResourcesFiles(String resourceDir) {
        List<String> fileList = new ArrayList<>();
        try {
            // 获取ClassLoader
            ClassLoader classLoader = Tools.class.getClassLoader();
            // 获取资源目录的URL
            URL url = classLoader.getResource(resourceDir);

            if (url == null) {
                System.err.println("资源目录未找到：" + resourceDir);
                return fileList;
            }

            // 判断资源是否在JAR包中
            if ("jar".equals(url.getProtocol())) {
                // 处理JAR包内的资源
                JarURLConnection jarConnection = (JarURLConnection) url.openConnection();
                try (JarFile jarFile = jarConnection.getJarFile()) {
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        String entryName = entry.getName();
                        // 过滤出属于指定目录且是文件的条目
                        if (entryName.startsWith(resourceDir + "/") && !entry.isDirectory()) {
                            fileList.add(entryName);
                        }
                    }
                }
            } else {
                // 处理文件系统中的资源
                File dir = new File(url.toURI());
                if (dir.exists() && dir.isDirectory()) {
                    fileList = Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                            .filter(File::isFile)
                            .map(file -> resourceDir + File.separator + file.getName())
                            .collect(Collectors.toList());
                }
            }
        } catch (Exception e) {
            System.err.println("获取资源文件失败：" + e.getMessage());
        }
        return fileList;
    }


    /**
     * 读取资源文件内容
     * @param resourcePath 资源文件路径
     * @return 资源文件内容的列表
     */
    public static Deque<String> readResourceFile(String resourcePath) {
        Deque<String> content = new ArrayDeque<>();
        try (InputStream inputStream = DataBaseParser.class.getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                System.err.println("资源文件未找到：" + resourcePath);
                return content;
            }

            String line;
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }
        } catch (IOException e) {
            System.err.println("读取资源文件失败：" + e.getMessage());
        }
        return content;
    }

    /**
     * 创建临时目录
     * @param filePath
     */
    public static void createDir(String filePath) {
        File tempDirectory = new File(filePath);
        if (!tempDirectory.exists()) {
            tempDirectory.mkdirs();
        } else {
            boolean res = Tools.deleteDirectory(tempDirectory);
            if (!res) {
                throw new RuntimeException("删除临时目录失败");
            }
            tempDirectory.mkdirs();
        }
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
     * 替换模板中的关键词
     * @param data
     * @param template
     * @return
     * @param <T>
     */
    public static <T> String replaceData(T data, List<String> template) {
        // 合并集合
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : template) {
            stringBuilder.append(s + "\n");
            if (s.endsWith(";") || s.endsWith("}")) {
                stringBuilder.append("\n");
            }
        }

        String str = stringBuilder.toString();

        // 提取关键词
        Set<String> keys = extractKeys(str, "\\$\\([a-zA-Z]+\\)");

        for (String key : keys) {
            try {
                // 获取类实例
                Class<?> clazz = data.getClass();

                // 获取方法名
                String methodName = "get" + key.replace("$(", "").replace(")", "");

                // 获取方法
                Method method = clazz.getMethod(methodName);

                // 调用方法
                String field = (String) method.invoke(data);

                // 替换字符串中的关键词
                str =  str.replace(key, field);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return str;
    }

    /**
     * 创建文件夹
     * @param path
     * @return
     */
    public static boolean mkdir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }


    /**
     * 合并多个set
     * @param sets
     * @return
     * @param <T>
     */
    public static <T> Set<T> mergeSet(Set<T>... sets) {
        Set<T> result = new HashSet<>();
        if (sets == null) {
            return result;
        }
        for (Set<T> set : sets) {
            if (set != null) {
                result.addAll(set);
            }
        }
        return result;
    }

    /**
     * 合并多个List
     * @param lists
     * @return
     * @param <T>
     */
    public static <T> List<T> mergeList(List<T>... lists) {
        List<T> result = new ArrayList<>();
        if (lists == null) {
            return result;
        }
        for (List<T> list : lists) {
            if (list != null) {
                result.addAll(list);
            }
        }
        return result;
    }

    /**
     * 判断模板中是否包含关键词
     * @param template
     * @param str
     * @return
     */
    public static Boolean isTemplateHas(Template template, String str) {
        List<String> frame = template.getFrame();
        List<String> variable = template.getVariable();
        List<String> methods = template.getMethods();
        List<String> list = mergeList(frame, variable, methods);

        String context = String.join("\n", list);

        return context.contains(str);
    }
}
