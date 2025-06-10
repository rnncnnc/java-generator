package com.qinge.backend.builder.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qinge.backend.entity.table.Table;
import com.qinge.backend.entity.template.object.xml.Tag;
import com.qinge.backend.entity.template.object.xml.XmlFile;
import com.qinge.backend.utils.ClassTools;
import com.qinge.backend.utils.StringTools;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Data: 2025/6/10 14:28
 * @Author: Lc
 * @Description:
 */


public class MapperBuilder extends XmlBuilder {

    private Table table;


    // 构建前 替换关键字
    @Override
    XmlFile beforeWrite(XmlFile xmlFile) throws JsonProcessingException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        XmlFile xmlFileCopy = ClassTools.deepCopy(xmlFile);

        replaceList(xmlFileCopy.getTags());

        return replaceKeyword(xmlFileCopy, table);
    }

    /**
     * 替换tag里面的List标签
     * @param tags
     */
    private void replaceList(List<Tag> tags) throws JsonProcessingException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        if (CollectionUtils.isEmpty(tags)) {
            return;
        }

        for (Tag tag : tags) {
            // 如果tag的标签是List,则替换关键字
            if (tag.getName().equals("List")) {
                List<Tag> replacedList = getReplacedList(tag);

                // 替换replaceList里面的List标签
                replaceList(replacedList);

                // 将List标签移除，并将替换后的标签添加到原位置
                tags.addAll(tags.indexOf(tag), replacedList);
                tags.remove(tag);
                return;
            } else {
                // 如果tag的标签不是List,则递归调用replaceList方法
                replaceList(tag.getTags());
            }
        }
    }

    /**
     * 获取替换关键字后的list
     * @param tag
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws JsonProcessingException
     */
    private List<Tag> getReplacedList(Tag tag) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, JsonProcessingException {
        // 获取要替换的字段
        String value = tag.getParams().get(0).getValue();

        List<Tag> tags = tag.getTags();

        // 获取要替换的字段的信息
        List<Object> list = (List<Object>) ClassTools.runMethodByMethodName(table, "get" + StringTools.firstToUppercase(value) + "s");

        List<Tag> tagList = new ArrayList<>();
        // 替换关键字
        for (Object obj : list) {
            for (Tag tagObj : tags) {
                Tag tagCopy = ClassTools.deepCopy(tagObj);

                Tag tagRep = replaceKeyword(tagCopy, obj);
                tagList.add(tagRep);
            }
        }

        return tagList;
    }
}
