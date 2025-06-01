package com.qinge.backend.service.Impl;
import java.util.List;
import org.springframework.stereotype.Service;
import com.qinge.backend.entity.pojo.GroupInfoPojo;
import com.qinge.backend.service.Impl.entity.query.Query;
import jakarta.annotation.Resource;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



@Service
public class GroupInfoServiceImpl implements GroupInfoService<GroupInfoPojo> {

    @Resource
    private GroupInfoMapper groupInfoMapper;


    /**
     * 查找符合类型的所有数据
     * @param query
     * @return
     */
    @Override
    public List<GroupInfoPojo> select(Query<GroupInfoPojo> query) {
        return groupInfoMapper.select(query);

    }

    /**
     * 插入数据
     * @param query
     */
    @Override
    public void insert(Query<GroupInfoPojo> query) {
        groupInfoMapper.insert(query);

    }

    /**
     * 删除数据
     * @param query
     */
    @Override
    public void delete(Query<GroupInfoPojo> query) {
        groupInfoMapper.delete(query);

    }


}