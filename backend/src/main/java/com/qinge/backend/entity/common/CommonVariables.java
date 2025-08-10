package com.qinge.backend.entity.common;

import com.qinge.backend.utils.StringTools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Data: 2025/8/10 21:41
 * @Author: Lc
 * @Description:
 */


public class CommonVariables {

    private String basePackage;

    public String getDateTime() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date());
    }

    public String getBasePackage() {
        return StringTools.isEmpty(basePackage) ? "com.qinge.example" : basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
