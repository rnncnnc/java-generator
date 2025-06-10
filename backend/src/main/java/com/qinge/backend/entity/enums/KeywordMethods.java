package com.qinge.backend.entity.enums;

import com.qinge.backend.entity.table.Field;
import com.qinge.backend.entity.table.Index;
import com.qinge.backend.entity.table.Table;

import java.lang.reflect.Method;

/**
 * @Data: 2025/6/6 12:06
 * @Author: Lc
 * @Description:
 */


public enum KeywordMethods {
    TABLENAME("TableName", new Object() {
        Method evaluate() {
            try {
                return Table.class.getMethod("getTableName");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),
    TABLEORIGINNAME("TableOriginName", new Object() {
        Method evaluate() {
            try {
                return Table.class.getMethod("getTableOriginName");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),
    LTABLENAME("LTableName", new Object() {
        Method evaluate() {
            try {
                return Table.class.getMethod("getLTableName");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),
    TABLECOMMENT("TableComment", new Object() {
        Method evaluate() {
            try {
                return Table.class.getMethod("getTableComment");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),
    DATETIME("DateTime", new Object() {
        Method evaluate() {
            try {
                return Table.class.getMethod("getDateTime");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),
    BASEPACKAGE("BasePackage", new Object() {
        Method evaluate() {
            try {
                return Table.class.getMethod("getBasePackage");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),

    FIELDNAME("FieldName", new Object() {
        Method evaluate() {
            try {
                return Field.class.getMethod("getFieldName");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),
    LFIELDNAME("UFieldName", new Object() {
        Method evaluate() {
            try {
                return Field.class.getMethod("getUFieldName");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),

    FIELDCOMMENT("FieldComment", new Object() {
        Method evaluate() {
            try {
                return Field.class.getMethod("getFieldComment");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),
    FIELDCOLUMN("FieldColumn", new Object() {
        Method evaluate() {
            try {
                return Field.class.getMethod("getFieldColumn");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),
    FIELDDATATYPE("FieldDataType", new Object() {
        Method evaluate() {
            try {
                return Field.class.getMethod("getFieldDataType");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),
    FIELDKEY("FieldKey", new Object() {
        Method evaluate() {
            try {
                return Field.class.getMethod("getFieldKey");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),

    INDEXNAME("IndexName", new Object() {
        Method evaluate() {
            try {
                return Index.class.getMethod("getIndexName");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),
    INDEXCOLUMN("IndexColumn", new Object() {
        Method evaluate() {
            try {
                return Index.class.getMethod("getIndexColumn");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),
    INDEXFIELD("IndexField", new Object() {
        Method evaluate() {
            try {
                return Index.class.getMethod("getIndexField");
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate())
    ;

    private String name;

    private Method method;

    /**
     * 根据名称获取方法
     * @param name
     * @return
     */
    public static Method getByName(String name) {
        for (KeywordMethods keywordMethods : KeywordMethods.values()) {
            if (keywordMethods.getName().equals(name)) {
                return keywordMethods.getMethod();
            }
        }
        return null;
    }

    KeywordMethods(String name, Method method) {
        this.name = name;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public Method getMethod() {
        return method;
    }
}
