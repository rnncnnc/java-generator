package com.qinge.backend.service.Impl;
import java.util.List;
import org.springframework.stereotype.Service;
import com.qinge.backend.service.Impl.entity.query.Query;
import jakarta.annotation.Resource;
import com.qinge.backend.entity.pojo.ChatSessionUserPojo;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



@Service
public class ChatSessionUserServiceImpl implements ChatSessionUserService<ChatSessionUserPojo> {

    @Resource
    private ChatSessionUserMapper chatSessionUserMapper;


    /**
     * 查找符合类型的所有数据
     * @param query
     * @return
     */
    @Override
    public List<ChatSessionUserPojo> select(Query<ChatSessionUserPojo> query) {
        return chatSessionUserMapper.select(query);

    }

    /**
     * 插入数据
     * @param query
     */
    @Override
    public void insert(Query<ChatSessionUserPojo> query) {
        chatSessionUserMapper.insert(query);

    }

    /**
     * 删除数据
     * @param query
     */
    @Override
    public void delete(Query<ChatSessionUserPojo> query) {
        chatSessionUserMapper.delete(query);

    }


}