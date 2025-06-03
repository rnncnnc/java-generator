package com.qinge.backend.parser;

import com.qinge.backend.builder.Builder;
import com.qinge.backend.entity.constants.ClassDir;
import com.qinge.backend.entity.dto.BaseInfo;
import com.qinge.backend.entity.dto.template.FileObj;
import com.qinge.backend.entity.dto.template.Template;
import com.qinge.backend.entity.dto.template.java.JavaClass;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Data: 2025/6/1 11:19
 * @Author: Lc
 * @Description:
 */


public class JavaParser implements Parser {

    /**
     * 将map转为template
     * @param templateMap
     * @return
     */
    @Override
    public FileObj parseTemplate(Map<String, Object> templateMap) {
        JavaClass javaClass = new JavaClass();

        BeanUtils.
        BeanUtils.populate(javaClass, templateMap);

        // javaClass.setPackageName((String) templateMap.get("packageName"));
        //
        // List parents = (List) templateMap.get("parents");
        //
        //
        //
        // System.out.println(javaClass);

        return javaClass;
    }

    // public <T> List<T> mapToList(Object object) {
    //     List list = (List) object;
    //
    //     List<T> result = new ArrayList<>();
    //
    //     if (list == null) {
    //         return result;
    //     }
    //
    //
    //
    //     for (Object o : list) {
    //
    //
    //         BeanUtils.populate(t, o);
    //     }
    // }
}
