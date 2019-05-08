package org.springblade.information.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.ChatMessage;
import org.springblade.common.entity.ChatSession;
import org.springblade.common.entity.UserEntity;
import org.springblade.information.feign.UserServiceFeign;
import org.springblade.information.mapper.ChatMessageDao;
import org.springblade.information.service.ChatMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author hanbin
 * 聊天消息业务层实现类
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageDao, ChatMessage> implements ChatMessageService {


    @Resource
    UserServiceFeign userService;

    @Override
    public List<ChatSession> batchGetUnreadMessage(Long userId, List<ChatSession> chatSessions) {

        QueryWrapper<ChatSession> wrapper = new QueryWrapper<>();
        wrapper.select("`from`", "`to`", "count(1) as `unread`");
        wrapper.eq("`to`", userId);
        wrapper.eq("`read`", false);
        wrapper.groupBy(true, "`from`,`to`");
        List<Map<String, Object>> mapList = baseMapper.selectMaps((Wrapper) wrapper);

        Set<Long> userIds = new HashSet<>(16);

        for (ChatSession session : chatSessions) {

            userIds.add(session.getFrom().longValue());

            for (Map<String, Object> map : mapList) {
                if (session.getFrom().intValue() == ((Integer) map.get("from")).intValue() &&
                        session.getTo().intValue() == ((Integer) map.get("to")).intValue()) {
                    session.setUnread(((Long) map.get("unread")).intValue());
                }
            }
        }

        org.springblade.core.tool.api.R<Collection<UserEntity>> r = userService.batchGetUserByIds(userIds);

        Collection<UserEntity> userCollection = r.getData();

        for (ChatSession session : chatSessions) {

            for(UserEntity userEntity : userCollection){
                if(session.getFrom().longValue() == userEntity.getUserId()){
                    session.setFromUser(userEntity);
                }
            }
        }

        return chatSessions;
    }

}
