package com.qinge.backend.builder;

import com.qinge.backend.entity.dto.Field;
import com.qinge.backend.entity.dto.Table;
import com.qinge.backend.entity.dto.Template;
import com.qinge.backend.utils.Tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Data: 2025/5/31 0:35
 * @Author: Lc
 * @Description:
 */


public abstract class Builder {

    protected Table table;

    protected Template template;

    protected BufferedWriter bw;

    protected String basePath;

    protected String basePackageName;

    protected String packageName;

    protected String filePath;

    protected Set<String> importList;

    /**
     * 构建文件
     */
    public void build() {

        // 创建文件
        File file = new File(filePath);

        FileWriter fw = null;

        try  {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);

            // 写入包名
            writePackage(bw, packageName);

            // 提取注解
            importList = Tools.mergeSet(extractAnnotation(), extractKeyword());
            extra();

            // 写入import
            writeImport(bw, importList);

            // 写入描述
            writeDescription(bw);

            // 写入类框架
            writeFrame(bw, table, template.getFrame());

            // 写入类变量
            writeVariable(bw, table.getFields(), template.getVariable());

            // 写入类方法
            writeMethod(bw, table.getFields(), template.getMethods());

            // 收尾
            bw.write("}");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 构建pojo时的额外操作
     */
    protected void extra() {}

    /**
     * 提取注解
     * @return
     */
    abstract protected Set<String> extractAnnotation();

    /**
     * 提取关键词
     * @return
     */
    abstract protected Set<String> extractKeyword();

    /**
     * 写入包名
     * @param bw
     * @param packageName
     */
    protected void writePackage(BufferedWriter bw, String packageName) {
        try {
            bw.write("package " + packageName + ";\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 写入import内容
     * @param bw
     * @param importList
     */
    protected void writeImport(BufferedWriter bw, Set<String> importList) {
        try {
            // 写入import
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : importList) {
                stringBuilder.append("import " + s + ";\n");
            }
            bw.write(stringBuilder.toString());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 写入类描述
     * @param bw
     * @throws IOException
     */
    protected void writeDescription(BufferedWriter bw) {
        /**
         * @Data: 2025/5/30 15:12
         * @Author: Lc
         * @Description:
         */

        try {
            bw.write("/**\n");
            bw.write(" * @Data: " + new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date()) + "\n");
            bw.write(" * @Author: Lc\n");
            bw.write(" * @Description: \n");
            bw.write(" */\n");
            bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 写入类框架
     * @param bw
     * @param table
     * @param frame
     * @throws IOException
     */
    protected void writeFrame(BufferedWriter bw, Table table, List<String> frame) {
        try {
            List<String> temp = new ArrayList<>(frame);
            // 写入类开头
            String classStart = Tools.replaceData(table, temp);

            bw.write(classStart);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 写入变量
     * @param bw
     * @param fields
     * @param variable
     * @throws IOException
     */
    protected void writeVariable(BufferedWriter bw, List<Field> fields, List<String> variable) {
        try {
            List<String> temp = new ArrayList<>(variable);

            if(temp.isEmpty()) {
                return;
            }

            // 替换掉关键词 $(TableName)
            String context = Tools.replaceData(table, temp);

            // 替换掉关键词标记 ^List
            context = context.replace("^", "");

            bw.write(context);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 写入类方法
     * @param bw
     * @param fields
     * @param methods
     */
    protected void writeMethod(BufferedWriter bw, List<Field> fields, List<String> methods) {
        try {
            List<String> temp = new ArrayList<>(methods);

            if(temp.isEmpty()) {
                return;
            }

            String context = Tools.replaceData(table, temp);
            context = context.replace("^", "");

            bw.write(context);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }
}
