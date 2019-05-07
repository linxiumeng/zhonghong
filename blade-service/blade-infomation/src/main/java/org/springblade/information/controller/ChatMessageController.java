package org.springblade.information.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.entity.ChatMessage;
import org.springblade.common.entity.ChatSession;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.form.ChatMessageForm;
import org.springblade.common.utils.R;
import org.springblade.information.service.ChatMessageService;
import org.springblade.information.service.ChatSessionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hanbin
 * 聊天消息api类
 */
@RestController
@RequestMapping("/api/chat")
public class ChatMessageController {

    @Resource
    ChatMessageService chatMessageService;

    @Resource
    ChatSessionService chatSessionService;

    @PostMapping("list")
    public R listChatMessage(@RequestBody ChatMessageForm param){

        IPage<ChatMessage> chatMessagePage = new Page<>(1,param.getSize());

        QueryWrapper<ChatMessage> wrapper = new QueryWrapper<>();
        wrapper
                .eq("from",param.getFrom())
                .eq("to",param.getTo())
                .lt("create_time",param.getFromDate())
                .orderBy(true,false,"create_time");

        //todo 这里需要优化不需要查询总条数
        IPage<ChatMessage> resultPage = chatMessageService.page(chatMessagePage,wrapper);

        List<ChatMessage> messageList = resultPage.getRecords();
        Collections.reverse(messageList);

        return R.ok().put("result",resultPage);
    }

    @PostMapping("read")
    public boolean batchReadMessages(@RequestParam("ids")long[] ids){

        List<ChatMessage> list = new LinkedList<>();

        for(long id:ids){
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setId(id);
            chatMessage.setRead(true);
            list.add(chatMessage);
        }

        return chatMessageService.updateBatchById(list);
    }


    /**
     * 列出当前人的会话列表
     * @return
     */
    @Login
    @GetMapping("conversations")
    public R listSessions(@LoginUser UserEntity user){
        QueryWrapper<ChatSession> wrapper = new QueryWrapper<>();
        wrapper.eq("`to`",user.getUserId()).orderBy(true,false,"`status`").orderBy(true,false,"update_date");
        List<ChatSession> sessions = chatSessionService.list(wrapper);
        return R.ok().put("result",sessions);
    }

    /**
     * 移除一个会话列表
     * @param sessionId
     * @return
     */
    @PostMapping("remove_conversation")
    public R removeSession(@RequestParam("id") Long sessionId){
        chatSessionService.removeById(sessionId);
        return R.ok();
    }

}
