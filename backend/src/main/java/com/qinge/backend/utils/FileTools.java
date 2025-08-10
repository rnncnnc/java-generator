package com.qinge.backend.utils;

import com.qinge.backend.entity.common.FileObj;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Data: 2025/5/30 15:25
 * @Author: Lc
 * @Description:
 */


public class FileTools {

    /**
     * 复制文件夹并修改目标文件夹名称
     * @param sourceFolder 源文件夹
     * @param targetFolder 目标文件夹
     * @return 是否复制成功
     */
    public static boolean copyAndRenameFolder(File sourceFolder, File targetFolder) {
        Path sourcePath = sourceFolder.toPath();
        Path targetParentPath = targetFolder.getParentFile().toPath();
        // 构建目标文件夹完整路径（父目录 + 新名称）
        Path targetPath = targetFolder.toPath();

        // 校验源文件夹是否存在
        if (!Files.exists(sourcePath) || !Files.isDirectory(sourcePath)) {
            System.out.println("源文件夹不存在或不是目录：" + sourceFolder.getName());
            return false;
        }

        // 校验目标父目录是否存在，不存在则创建
        try {
            if (!Files.exists(targetParentPath)) {
                Files.createDirectories(targetParentPath);
            }
        } catch (IOException e) {
            System.err.println("创建目标父目录失败：" + e.getMessage());
            return false;
        }

        // 检查目标文件夹是否已存在
        if (Files.exists(targetPath)) {
            System.out.println("目标文件夹已存在：" + targetPath.toAbsolutePath());
            return false;
        }

        // 递归复制文件夹内容
        try {
            Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
                // 访问目录时创建对应的目标目录
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    // 计算相对路径（源目录下的子目录结构）
                    Path relativePath = sourcePath.relativize(dir);
                    // 创建目标目录（目标根目录 + 相对路径）
                    Path targetDir = targetPath.resolve(relativePath);
                    Files.createDirectories(targetDir);
                    return FileVisitResult.CONTINUE;
                }

                // 访问文件时复制文件到目标目录
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    // 计算相对路径
                    Path relativePath = sourcePath.relativize(file);
                    // 目标文件路径
                    Path targetFile = targetPath.resolve(relativePath);
                    // 复制文件（保持属性）
                    Files.copy(file, targetFile, StandardCopyOption.COPY_ATTRIBUTES);
                    return FileVisitResult.CONTINUE;
                }
            });

            System.out.println("文件夹复制并重命名成功：" + targetPath.toAbsolutePath());
            return true;
        } catch (IOException e) {
            System.err.println("文件夹复制失败：" + e.getMessage());
            // 清理可能的部分复制结果
            deleteDirectory(targetPath.toFile());
            return false;
        }
    }


    /**
     * 修改文件名称
     * @param oldFilePath 原文件完整路径（如 /data/oldFile.txt）
     * @param newFileName 新文件名称（含扩展名，如 newFile.txt）
     * @return 是否修改成功
     */
    public static boolean renameFile(String oldFilePath, String newFileName) {
        Path oldPath = Paths.get(oldFilePath);
        Path parentDir = oldPath.getParent();

        // 校验原文件存在且为文件
        if (!Files.exists(oldPath) || !Files.isRegularFile(oldPath)) {
            throw new IllegalArgumentException("原文件不存在或不是文件: " + oldFilePath);
        }

        // 构建新文件路径
        Path newPath = parentDir.resolve(newFileName);

        // 检查新文件是否已存在（避免覆盖）
        if (Files.exists(newPath)) {
            throw new IllegalArgumentException("新文件已存在，避免覆盖: " + newPath);
        }

        try {
            // 仅执行重命名，不涉及任何内容操作
            Files.move(oldPath, newPath);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("重命名失败: " + e.getMessage(), e);
        }
    }


    /**
     * 移动文件到目标文件夹
     * @param sourceFilePath 源文件完整路径（如 /data/file.txt）
     * @param targetDirPath 目标文件夹路径（如 /backup/）
     * @return 是否移动成功
     */
    public static boolean moveFile(String sourceFilePath, String targetDirPath) {
        // 构建源文件和目标文件夹的 Path 对象
        Path sourcePath = Paths.get(sourceFilePath);
        Path targetDir = Paths.get(targetDirPath);

        // 1. 校验源文件是否存在且是文件
        if (!Files.exists(sourcePath) || !Files.isRegularFile(sourcePath)) {
            System.out.println("源文件不存在或不是文件：" + sourceFilePath);
            return false;
        }

        // 2. 校验目标文件夹是否存在，不存在则创建
        try {
            if (!Files.exists(targetDir)) {
                Files.createDirectories(targetDir); // 递归创建父目录
                System.out.println("目标文件夹不存在，已自动创建：" + targetDirPath);
            }
        } catch (Exception e) {
            System.err.println("创建目标文件夹失败：" + e.getMessage());
            return false;
        }

        // 3. 构建目标文件路径（目标文件夹 + 源文件名）
        Path targetPath = targetDir.resolve(sourcePath.getFileName());

        // 4. 检查目标文件是否已存在，存在则覆盖（或根据需求处理）
        try {
            // 移动文件：REPLACE_EXISTING 表示覆盖已存在的目标文件
            Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("文件移动成功：" + sourceFilePath + " -> " + targetPath);
            return true;
        } catch (Exception e) {
            System.err.println("文件移动失败：" + e.getMessage());
            return false;
        }
    }


    /**
     * 创建文件并设置内容
     * @param file 文件对象
     * @param content 内容
     * @return 是否创建成功
     */
    public static Boolean createFileAndSetContent(File file, String content) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }



    /**
     * 读取目录下的所有文件和文件夹
     * @param dir 目录对象
     * @return 文件对象列表
     */
    public static List<FileObj> readDir(File dir) {
        List<FileObj> result = new ArrayList<>();


        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files == null) {
                return result;
            }

            for (File file : files) {
                if (file.isDirectory()) {
                    result.add(new FileObj(file.getName(), file.getPath(), readDir(file)));
                } else {
                    result.add(new FileObj(file.getName(), file.getPath(), null));
                }
            }
        }
        return result;
    }

    /**
     * 读取目录下的所有文件和文件夹
     * @param dir 目录对象
     * @return 文件对象列表
     */
    public static List<FileObj> readDirWithoutPath(File dir) {
        List<FileObj> result = new ArrayList<>();


        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files == null) {
                return result;
            }

            for (File file : files) {
                if (file.isDirectory()) {
                    result.add(new FileObj(file.getName(), null, readDirWithoutPath(file)));
                } else {
                    result.add(new FileObj(file.getName(), null, null));
                }
            }
        }
        return result;
    }

    /**
     * 使用 NIO 方式修改文件夹名称（推荐）
     * @param oldFolderPath 原文件夹路径
     * @param newFolderName 新文件夹名称（仅名称，不含路径）
     * @return 是否修改成功
     */
    public static boolean renameFolder(String oldFolderPath, String newFolderName) {
        Path oldFolder = Paths.get(oldFolderPath);

        // 校验原文件夹是否存在且为目录
        if (!Files.exists(oldFolder) || !Files.isDirectory(oldFolder)) {
            System.out.println("原文件夹不存在或不是目录：" + oldFolderPath);
            return false;
        }

        // 构建新文件夹路径
        Path parentDir = oldFolder.getParent();
        if (parentDir == null) {
            System.out.println("无法获取父目录：" + oldFolderPath);
            return false;
        }
        Path newFolder = parentDir.resolve(newFolderName);

        // 检查新文件夹是否已存在
        if (Files.exists(newFolder)) {
            System.out.println("新文件夹已存在：" + newFolder.toAbsolutePath());
            return false;
        }

        try {
            // 执行重命名
            Files.move(oldFolder, newFolder);
            System.out.println("文件夹重命名成功：" + newFolder.toAbsolutePath());
            return true;
        } catch (Exception e) {
            System.err.println("文件夹重命名失败：" + e.getMessage());
            return false;
        }
    }


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
        try (InputStream inputStream = FileTools.class.getClassLoader().getResourceAsStream(resourcePath);
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

    /**
     * 将文件打包成压缩包
     * @param filePath 文件列表
     * @return 压缩包路径
     */
    public static String zipFiles(String filePath, String zipName) {

        Path path = Paths.get(filePath);

        // 生成压缩包路径
        File parentFile = path.getParent().toFile();
        String zipFilePath = parentFile.getAbsolutePath() + File.separator + zipName + ".zip";

        // 将文件打包成压缩包
        try(
                FileOutputStream fos = new FileOutputStream(zipFilePath);
                ZipOutputStream zos = new ZipOutputStream(fos); // 获取压缩包的文件流
        ){

            File sourceDir = new File(filePath);
            File[] files = sourceDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    addFileToZip(sourceDir, file, zos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipFilePath;
    }

    /**
     * 辅助方法：递归添加文件到ZIP流中
     * @param baseDir
     * @param currentFile
     * @param zos
     * @throws IOException
     */
    private static void addFileToZip(File baseDir, File currentFile, ZipOutputStream zos) throws IOException {
        String entryName = baseDir.toPath().relativize(currentFile.toPath()).toString();

        if (currentFile.isDirectory()) {
            zos.putNextEntry(new ZipEntry(entryName + "/"));
            zos.closeEntry();
            File[] subFiles = currentFile.listFiles();
            if (subFiles != null) {
                for (File subFile : subFiles) {
                    addFileToZip(baseDir, subFile, zos);
                }
            }
        } else {
            zos.putNextEntry(new ZipEntry(entryName));
            try (FileInputStream fis = new FileInputStream(currentFile)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
            }
            zos.closeEntry();
        }
    }


}
