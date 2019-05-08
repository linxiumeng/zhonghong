package org.springblade.information.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.ChatMessage;
import org.springblade.common.entity.ChatSession;

import java.util.List;

/**
 * @author hanbin
 * 聊天消息业务接口
 */
public interface ChatMessageService extends IService<ChatMessage> {


    /**
     * 批量获取消息未读数
     * @param fromAndToString
     * @return
     */
    List<ChatSession> batchGetUnreadMessage(Long userId, List<ChatSession> fromAndToString);


}
