package org.springblade.information.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.finepetro.dao.ChatSessionDao;
import io.finepetro.entity.ChatSession;
import io.finepetro.service.ChatSessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hanbin
 * 聊天消息业务层实现类
 */
@Service
public class ChatSessionServiceImpl extends ServiceImpl<ChatSessionDao,ChatSession> implements ChatSessionService {

    @Resource
    private ChatSessionDao chatSessionDao;

    @Override
    public boolean insertOrUpdate(ChatSession chatSession, boolean needOverride) {

        if(needOverride){
            return this.insertOrUpdateOverride(chatSession);
        }else{
            return this.insertOrUpdate(chatSession);
        }

    }

    /**
     * 重写insertOrUpdate方法
     * @param chatSession
     * @return
     */
    private boolean insertOrUpdateOverride(ChatSession chatSession){
        return chatSessionDao.insertOrUpdateWithSql(chatSession) > 0;
    }


}
