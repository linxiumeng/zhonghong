package org.springblade.information.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.ChatSession;

/**
 * @author hanbin
 * 聊天会话业务接口
 */
public interface ChatSessionService extends IService<ChatSession> {

    /**
     * 更新或插入
     * @param chatSession
     * @param needOverride
     * @return
     */
    boolean insertOrUpdate(ChatSession chatSession, boolean needOverride);
}
