package com.qinge.backend.entity.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @Data: 2025/6/10 23:45
 * @Author: Lc
 * @Description:
 */


public enum DBKeys {

    PRIMARY("Primary", Arrays.asList("PRIMARY", "PRI")),
    INDEX("Index", Arrays.asList("INDEX", "MUL")),
    UNIQUE("Unique", Arrays.asList("UNIQUE", "UNI"))

    ;
    private String name;

    private List<String> index;

    DBKeys(String name, List<String> index) {
        this.name = name;
        this.index = index;
    }

    public static String getByIndex(String index) {
        for (DBKeys value : DBKeys.values()) {
            if (value.getIndex().contains(index)) {
                return value.getName();
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public List<String> getIndex() {
        return index;
    }
}
