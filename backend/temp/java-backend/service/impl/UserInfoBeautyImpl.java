package com.qinge.backend.service.Impl;
import java.util.List;
import com.qinge.backend.entity.pojo.UserInfoBeautyPojo;
import org.springframework.stereotype.Service;
import com.qinge.backend.service.Impl.entity.query.Query;
import jakarta.annotation.Resource;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



@Service
public class UserInfoBeautyServiceImpl implements UserInfoBeautyService<UserInfoBeautyPojo> {

    @Resource
    private UserInfoBeautyMapper userInfoBeautyMapper;


    /**
     * 查找符合类型的所有数据
     * @param query
     * @return
     */
    @Override
    public List<UserInfoBeautyPojo> select(Query<UserInfoBeautyPojo> query) {
        return userInfoBeautyMapper.select(query);

    }

    /**
     * 插入数据
     * @param query
     */
    @Override
    public void insert(Query<UserInfoBeautyPojo> query) {
        userInfoBeautyMapper.insert(query);

    }

    /**
     * 删除数据
     * @param query
     */
    @Override
    public void delete(Query<UserInfoBeautyPojo> query) {
        userInfoBeautyMapper.delete(query);

    }


}