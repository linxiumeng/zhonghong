package org.springblade.information.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.ChatMessage;
import org.springblade.common.entity.ChatSession;
import org.springblade.information.mapper.ChatMessageDao;
import org.springblade.information.service.ChatMessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author hanbin
 * 聊天消息业务层实现类
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageDao, ChatMessage> implements ChatMessageService {


    @Override
    public List<ChatSession> batchGetUnreadMessage(Long userId, List<ChatSession> chatSessions) {

        QueryWrapper<ChatSession> wrapper = new QueryWrapper<>();
        wrapper.select("`from`","`to`","count(1) as `unread`");
        wrapper.eq("`to`",userId);
        wrapper.eq("`read`",false);
        wrapper.groupBy(true,"`from`,`to`");
        List<Map<String,Object>> mapList = baseMapper.selectMaps((Wrapper) wrapper);

        for(Map<String,Object> map : mapList){

            for(ChatSession session : chatSessions){

                if(session.getFrom().intValue() == ((Integer)map.get("from")).intValue() &&
                        session.getTo().intValue() == ((Integer)map.get("to")).intValue()){
                    session.setUnread(((Long) map.get("unread")).intValue());
                }
            }
        }

        return chatSessions;
    }

}
