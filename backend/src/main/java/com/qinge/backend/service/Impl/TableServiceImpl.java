package com.qinge.backend.service.Impl;

import com.qinge.backend.connector.DBConnector;
import com.qinge.backend.dto.DateBaseInfo;
import com.qinge.backend.entity.table.Table;
import com.qinge.backend.parser.database.DBParser;
import com.qinge.backend.service.TableService;
import com.qinge.backend.utils.ClassTools;
import com.qinge.backend.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Data: 2025/8/3 11:38
 * @Author: Lc
 * @Description:
 */


@Service
public class TableServiceImpl implements TableService {

    private final Logger log = LoggerFactory.getLogger(TableServiceImpl.class);

    /**
     * 解析数据库信息
     * @param baseInfo
     * @return
     */
    @Override
    public List<Table> getTableList(DateBaseInfo baseInfo) throws Exception {

        String dbType = baseInfo.getDbType();

        // 获取解析器的全类名
        String connectorClassName = DBConnector.class.getName().replace("DB", StringTools.firstToUppercase(dbType.toLowerCase()));

        // 构造连接器对象
        DBConnector connector = ClassTools.buildClassByFullName(connectorClassName);
        ClassTools.setFieldValue(connector, "url", baseInfo.getDbUrl());
        ClassTools.setFieldValue(connector, "username", baseInfo.getUsername());
        ClassTools.setFieldValue(connector, "password", baseInfo.getPassword());

        // 获取数据库解析器
        String parserClassName = DBParser.class.getName().replace("DB", StringTools.firstToUppercase(dbType.toLowerCase()));

        // 构建数据库解析器对象
        DBParser parser = ClassTools.buildClassByFullName(parserClassName);
        ClassTools.setFieldValue(parser, "connector", connector);
        ClassTools.setFieldValue(parser, "tablePrefix", baseInfo.getTablePrefix());
        ClassTools.setFieldValue(parser, "fieldSeparator", baseInfo.getFieldSeparator());

        // 解析数据库
        List<Table> tableList = parser.parseTableFromDB();

        log.info("解析数据库成功" + baseInfo.getDbType());

        return tableList;
    }
}
