package org.springblade.information.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springblade.common.entity.ChatSession;

/**
 * @author hanbin
 * 聊天会话数据存储层
 */
@Mapper
public interface ChatSessionDao extends BaseMapper<ChatSession> {

    /**
     * 更新
     * @param chatSession
     * @return
     */
    @Insert("insert into tb_chat_session(`from`,`to`) values(#{from},#{to}) ON DUPLICATE KEY update update_date = CURRENT_TIMESTAMP")
    int insertOrUpdateWithSql(ChatSession chatSession);
}
