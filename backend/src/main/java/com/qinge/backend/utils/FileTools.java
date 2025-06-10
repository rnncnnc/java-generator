package com.qinge.backend.utils;

import com.qinge.backend.parser.database.DataBaseParser;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * @Data: 2025/5/30 15:25
 * @Author: Lc
 * @Description:
 */


public class FileTools {

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
            ClassLoader classLoader = FileTools.class.getClassLoader();
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
            boolean res = FileTools.deleteDirectory(tempDirectory);
            if (!res) {
                throw new RuntimeException("删除临时目录失败");
            }
            tempDirectory.mkdirs();
        }
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


}
