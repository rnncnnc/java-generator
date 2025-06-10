package com.qinge.backend.parser.database;

import com.qinge.backend.connector.DBConnector;
import com.qinge.backend.entity.table.Field;
import com.qinge.backend.entity.table.Index;
import com.qinge.backend.entity.table.Table;
import com.qinge.backend.entity.enums.DBKeys;
import com.qinge.backend.entity.enums.DataFields;
import com.qinge.backend.entity.enums.dataType.DBType;
import com.qinge.backend.utils.StringTools;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Data: 2025/6/10 20:49
 * @Author: Lc
 * @Description:
 */


@Slf4j
public abstract class DBParser implements DataBaseParser {

    // 数据库连接器
    protected DBConnector connector;

    // 数据库枚举
    protected DataFields dbFields;

    // 表前缀
    protected String tablePrefix;

    // 字段分隔符
    protected String fieldSeparator;

    /**
     * 解析数据库
     * @return
     */
    abstract public List<Table> parseTableFromDB() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    // 获取数据库枚举变量
    abstract protected void getDataFields();

    // 获取数据库内所有表信息
    protected List<Table> getTables(String tableSql, String fieldSql, String indexSql) {
        // 获取数据库内所有表信息
        List<Map<String, Object>> tables = connector.executeQuery(tableSql);
        log.info("table sql 执行成功");

        List<Table> tableList = new ArrayList<>();
        for (Map<String, Object> table : tables) {

            // 存储表信息
            Table tab = new Table();

            // 表名
            String originTableName = (String) table.get(dbFields.getTableName());

            // 设置原始表名
            tab.setTableOriginName(originTableName);

            // 处理后的表名
            String tabName = originTableName;

            // 替换掉表的前缀
            if (tablePrefix != null) {
                tabName = ((String) table.get(dbFields.getTableName())).split(tablePrefix)[1];
            }

            // 将表名改为驼峰显示
            tabName = StringTools.toCamel(tabName, fieldSeparator);

            // 设置表名
            tab.setTableName(StringTools.firstToUppercase(tabName));

            // 设置表注释
            String tabComment = (String) table.get(dbFields.getTableComment());
            tab.setTableComment(tabComment == null ? "" : tabComment.trim());

            // 获取表的字段信息
            List<Field> fieldList = getFields(String.format(fieldSql, originTableName));
            tab.setFields(fieldList);

            // 获取表的索引信息
            List<Index> indexList = getIndexs(String.format(indexSql, originTableName));
            tab.setIndexs(indexList);

            tableList.add(tab);
        }

        return tableList;
    }

    // 获取表内所有字段信息
    protected List<Field> getFields(String sql) {
        // 获取表的字段信息
        List<Map<String, Object>> fields = connector.executeQuery(sql);
        log.info("fields sql 执行成功");

        List<Field> fieldList = new ArrayList<>();
        for (Map<String, Object> field : fields) {
            Field fed = new Field();

            // 设置原始字段名
            String columnName = (String) field.get(dbFields.getColumnName());
            fed.setFieldColumn(columnName);

            // 将字段名改为驼峰显示
            String fieldName = StringTools.toCamel(columnName, fieldSeparator);
            fed.setFieldName(fieldName);

            // 设置字段注释
            String fieldComment = (String) field.get(dbFields.getColumnComment());
            fed.setFieldComment(fieldComment.trim());

            // 去除括号  int(8)
            String dataType = (String) field.get(dbFields.getColumnType());
            if (dataType.contains("(")) {
                String[] split = dataType.split("\\(");
                dataType = split[0];
            }


            try {
                // 将mysql的数据类型转为java数据类型，并添加全类名
                Class<?> dataTypeConvert = DBType.getClassByType(this.getClass().getSimpleName().replace("Parser", ""));

                Method method = dataTypeConvert.getMethod("getByType", String.class);

                fed.setFieldDataType((String) method.invoke(null, dataType));
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            // 设置字段关键字
            fed.setFieldKey(DBKeys.getByIndex((String) field.get(dbFields.getColumnKey())));

            fieldList.add(fed);
        }

        return fieldList;
    };


    // 获取表内所有索引信息
    protected List<Index> getIndexs(String sql) {
        // 获取表的索引信息
        List<Map<String, Object>> indexs = connector.executeQuery(sql);
        log.info("index sql 执行成功");

        List<Index> indexList = new ArrayList<>();
        for (Map<String, Object> index : indexs) {
            Index ind = new Index();
            // 设置索引名
            ind.setIndexName((String) index.get(dbFields.getIndexName()));

            // 设置索引数据库名
            ind.setIndexColumn((String) index.get(dbFields.getIndexColumnName()));

            // 设置索引字段名
            ind.setIndexField(StringTools.toCamel((String) index.get(dbFields.getIndexColumnName()), fieldSeparator));

            indexList.add(ind);
        }

        return indexList;
    };
}
