package com.qinge.backend.service.Impl;
import java.util.List;
import org.springframework.stereotype.Service;
import com.qinge.backend.entity.pojo.AppUpdatePojo;
import com.qinge.backend.service.Impl.entity.query.Query;
import jakarta.annotation.Resource;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



@Service
public class AppUpdateServiceImpl implements AppUpdateService<AppUpdatePojo> {

    @Resource
    private AppUpdateMapper appUpdateMapper;


    /**
     * 查找符合类型的所有数据
     * @param query
     * @return
     */
    @Override
    public List<AppUpdatePojo> select(Query<AppUpdatePojo> query) {
        return appUpdateMapper.select(query);

    }

    /**
     * 插入数据
     * @param query
     */
    @Override
    public void insert(Query<AppUpdatePojo> query) {
        appUpdateMapper.insert(query);

    }

    /**
     * 删除数据
     * @param query
     */
    @Override
    public void delete(Query<AppUpdatePojo> query) {
        appUpdateMapper.delete(query);

    }


}