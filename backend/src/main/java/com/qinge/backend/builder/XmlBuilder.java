package com.qinge.backend.builder;

import com.qinge.backend.entity.dto.table.Table;
import com.qinge.backend.entity.dto.template.object.FileObject;
import com.qinge.backend.entity.dto.template.object.xml.Tag;
import com.qinge.backend.entity.dto.template.object.xml.TagParam;
import com.qinge.backend.entity.dto.template.object.xml.XmlFile;
import com.qinge.backend.utils.StringTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Data: 2025/6/7 0:58
 * @Author: Lc
 * @Description:
 */


@Slf4j
public abstract class XmlBuilder extends FileBuilder {

    /**
     * 构建文件
     * @param fileObj
     * @throws IOException
     */
    @Override
    public void build(FileObject fileObj) throws IOException {
        XmlFile xmlFile = (XmlFile) fileObj;

        // 构建前
        xmlFile = beforeWrite(xmlFile);

        // 文件路径
        String filePath = temPath + File.separator + xmlFile.getFilePath() + File.separator + xmlFile.getName() + ".xml";

        // 创建父目录
        File parentDir = new File(filePath).getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new IOException("父目录创建失败: " + parentDir.getAbsolutePath());
        }

        // 写入内容
        String content = writeContent(xmlFile);

        // 构建后
        content = afterWrite(content);

        // 写入文件(自动创建文件,若存在则覆盖)
        Files.write(Paths.get(filePath), content.getBytes());
        log.info("写入文件成功: {}", xmlFile.getName());
    }

    /**
     * 构建前
     * @param xmlFile
     * @return
     */
    XmlFile beforeWrite(XmlFile xmlFile) {
        return xmlFile;
    };

    /**
     * 构建后
     * @param content
     * @return
     */
    String afterWrite(String content) {
        return content;
    };

    /**
     * 写入内容
     * @param xmlFile
     * @return
     */
    protected String writeContent(XmlFile xmlFile) {
        StringBuilder builder = new StringBuilder();

        // 写入头部
        for (String str : xmlFile.getHeader()) {
            builder.append(str).append("\n");
        }

        // 写入标签列表
        writeTagList(builder, xmlFile.getTags(), 0);

        return builder.toString();
    }

    /**
     * 写入标签列表
     * @param builder
     * @param tagList
     * @param deepth 深度
     */
    protected void writeTagList(StringBuilder builder, List<Tag> tagList, Integer deepth) {
        if (CollectionUtils.isEmpty(tagList)) {
            return;
        }
        for (Tag tag : tagList) {
            writeTag(builder, tag, deepth);
        }
    }

    /**
     * 写入标签
     * @param builder
     * @param tag
     * @param deepth 深度
     */
    protected void writeTag(StringBuilder builder, Tag tag, Integer deepth) {
        if (tag == null) {
            return;
        }

        if (StringTools.isEmpty(tag.getName())) {
            throw new RuntimeException("模板错误,缺失数据");
        }

        // 写入注释
        writeComment(builder, tag.getComment(), deepth);

        // 如果标签名为NONE,则将标签属性的value值写入为文本
        if (tag.getName().equals("NONE")) {
            for (TagParam param : tag.getParams()) {
                this.writeString(builder, param.getValue() + "\n", deepth);
            }

            return;
        }

        // 写入标签
        this.writeString(builder, "<", deepth);
        builder.append(tag.getName());

        // 写入标签属性
        writeTagParams(builder, tag.getParams());

        builder.append(">\n");

        // 写入子标签
        if (!CollectionUtils.isEmpty(tag.getTags())) {
            for (Tag subTag : tag.getTags()) {
                writeTag(builder, subTag, deepth + 1);
            }
        }

        // 写入尾部
        this.writeString(builder, "</", deepth);
        builder.append(tag.getName());
        builder.append(">\n");
    }

    /**
     * 写入字符串
     * @param builder
     * @param content
     * @param deepth 深度
     */
    private XmlBuilder writeString(StringBuilder builder, String content, Integer deepth) {
        if (StringTools.isEmpty(content)) {
            return this;
        }

        for (int i = 0; i < deepth; i++) {
            builder.append("\t");
        }

        builder.append(content);

        return this;
    }

    /**
     * 写入注释
     * @param builder
     * @param comment
     * @param deepth 深度
     */
    protected void writeComment(StringBuilder builder, List<String> comment, Integer deepth) {
        if (!CollectionUtils.isEmpty(comment)) {
            for (String str : comment) {
                this.writeString(builder, "<!-- ", deepth)
                        .writeString(builder, str, deepth)
                        .writeString(builder, " -->\n", deepth);
            }
        }
    }

    /**
     * 写入标签属性
     * @param builder
     * @param tagParamList
     */
    protected void writeTagParams(StringBuilder builder, List<TagParam> tagParamList) {
        if (!CollectionUtils.isEmpty(tagParamList)) {
            for (TagParam param : tagParamList) {
                if (StringTools.isEmpty(param.getKey())) {
                    continue;
                }
                if (StringTools.isEmpty(param.getValue())) {
                    throw new RuntimeException("模板错误,缺失数据");
                }

                builder.append(" ")
                        .append(param.getKey())
                        .append("=\"")
                        .append(param.getValue())
                        .append("\"");
            }
        }
    }
}
