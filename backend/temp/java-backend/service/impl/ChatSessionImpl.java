package com.qinge.backend.service.Impl;
import com.qinge.backend.entity.pojo.ChatSessionPojo;
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
public class ChatSessionServiceImpl implements ChatSessionService<ChatSessionPojo> {

    @Resource
    private ChatSessionMapper chatSessionMapper;


    /**
     * 查找符合类型的所有数据
     * @param query
     * @return
     */
    @Override
    public List<ChatSessionPojo> select(Query<ChatSessionPojo> query) {
        return chatSessionMapper.select(query);

    }

    /**
     * 插入数据
     * @param query
     */
    @Override
    public void insert(Query<ChatSessionPojo> query) {
        chatSessionMapper.insert(query);

    }

    /**
     * 删除数据
     * @param query
     */
    @Override
    public void delete(Query<ChatSessionPojo> query) {
        chatSessionMapper.delete(query);

    }


}