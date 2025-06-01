package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.pojo.UserContactPojo;
import java.util.List;
import org.springframework.stereotype.Service;
import com.qinge.backend.service.Impl.entity.query.Query;
import jakarta.annotation.Resource;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



@Service
public class UserContactServiceImpl implements UserContactService<UserContactPojo> {

    @Resource
    private UserContactMapper userContactMapper;


    /**
     * 查找符合类型的所有数据
     * @param query
     * @return
     */
    @Override
    public List<UserContactPojo> select(Query<UserContactPojo> query) {
        return userContactMapper.select(query);

    }

    /**
     * 插入数据
     * @param query
     */
    @Override
    public void insert(Query<UserContactPojo> query) {
        userContactMapper.insert(query);

    }

    /**
     * 删除数据
     * @param query
     */
    @Override
    public void delete(Query<UserContactPojo> query) {
        userContactMapper.delete(query);

    }


}