package org.springblade.information.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.common.entity.ChatMessage;

/**
 * @author hanbin
 * 聊天消息数据存储层
 */
@Mapper
public interface ChatMessageDao extends BaseMapper<ChatMessage> {
}
