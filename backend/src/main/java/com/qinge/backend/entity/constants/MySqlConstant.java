package com.qinge.backend.entity.constants;

/**
 * @Data: 2025/5/30 22:47
 * @Author: Lc
 * @Description:
 */


public class MySqlConstant extends DBConstant {

    public static final String PRIMARY_KEY = "PRI";

    public static final String[] SQL_DATE_TIME_TYPE = new String[]{"datetime", "timestamp"};

    public static final String[] SQL_DATE_TYPE = new String[]{"date"};

    public static final String[] SQL_DECIMAL_TYPE = new String[]{"decimal", "double", "float"};

    public static final String[] SQL_STRING_TYPES = new String[]{"char", "varchar", "text", "meidumtext", "longtext"};

    public static final String[] SQL_INTEGER_TYPES = new String[]{"int", "tinyint"};

    public static final String[] SQL_LONG_TYPES = new String[]{"bigint"};
}
