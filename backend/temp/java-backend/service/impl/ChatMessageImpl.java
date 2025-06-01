package com.qinge.backend.service.Impl;
import java.util.List;
import org.springframework.stereotype.Service;
import com.qinge.backend.entity.pojo.ChatMessagePojo;
import com.qinge.backend.service.Impl.entity.query.Query;
import jakarta.annotation.Resource;

/**
 * @Data: 2025/06/01 00:27
 * @Author: Lc
 * @Description: 
 */



@Service
public class ChatMessageServiceImpl implements ChatMessageService<ChatMessagePojo> {

    @Resource
    private ChatMessageMapper chatMessageMapper;


    /**
     * 查找符合类型的所有数据
     * @param query
     * @return
     */
    @Override
    public List<ChatMessagePojo> select(Query<ChatMessagePojo> query) {
        return chatMessageMapper.select(query);

    }

    /**
     * 插入数据
     * @param query
     */
    @Override
    public void insert(Query<ChatMessagePojo> query) {
        chatMessageMapper.insert(query);

    }

    /**
     * 删除数据
     * @param query
     */
    @Override
    public void delete(Query<ChatMessagePojo> query) {
        chatMessageMapper.delete(query);

    }


}