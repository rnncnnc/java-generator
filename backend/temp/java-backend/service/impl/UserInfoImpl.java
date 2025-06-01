package com.qinge.backend.service.Impl;
import java.util.List;
import org.springframework.stereotype.Service;
import com.qinge.backend.entity.pojo.UserInfoPojo;
import com.qinge.backend.service.Impl.entity.query.Query;
import jakarta.annotation.Resource;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



@Service
public class UserInfoServiceImpl implements UserInfoService<UserInfoPojo> {

    @Resource
    private UserInfoMapper userInfoMapper;


    /**
     * 查找符合类型的所有数据
     * @param query
     * @return
     */
    @Override
    public List<UserInfoPojo> select(Query<UserInfoPojo> query) {
        return userInfoMapper.select(query);

    }

    /**
     * 插入数据
     * @param query
     */
    @Override
    public void insert(Query<UserInfoPojo> query) {
        userInfoMapper.insert(query);

    }

    /**
     * 删除数据
     * @param query
     */
    @Override
    public void delete(Query<UserInfoPojo> query) {
        userInfoMapper.delete(query);

    }


}