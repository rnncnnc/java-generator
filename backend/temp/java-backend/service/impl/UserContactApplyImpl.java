package com.qinge.backend.service.Impl;
import java.util.List;
import org.springframework.stereotype.Service;
import com.qinge.backend.entity.pojo.UserContactApplyPojo;
import com.qinge.backend.service.Impl.entity.query.Query;
import jakarta.annotation.Resource;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



@Service
public class UserContactApplyServiceImpl implements UserContactApplyService<UserContactApplyPojo> {

    @Resource
    private UserContactApplyMapper userContactApplyMapper;


    /**
     * 查找符合类型的所有数据
     * @param query
     * @return
     */
    @Override
    public List<UserContactApplyPojo> select(Query<UserContactApplyPojo> query) {
        return userContactApplyMapper.select(query);

    }

    /**
     * 插入数据
     * @param query
     */
    @Override
    public void insert(Query<UserContactApplyPojo> query) {
        userContactApplyMapper.insert(query);

    }

    /**
     * 删除数据
     * @param query
     */
    @Override
    public void delete(Query<UserContactApplyPojo> query) {
        userContactApplyMapper.delete(query);

    }


}