package org.springblade.information.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.ChatMessage;
import org.springblade.information.mapper.ChatMessageDao;
import org.springblade.information.service.ChatMessageService;
import org.springframework.stereotype.Service;

/**
 * @author hanbin
 * 聊天消息业务层实现类
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageDao, ChatMessage> implements ChatMessageService {
}
