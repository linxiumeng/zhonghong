package org.springblade.information.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hanbin
 * 聊天消息api类
 */
@RestController
@RequestMapping("/api/chat")
@Api(tags = "聊天消息操作控制器（message）", description = "真的可以随便填吗")
public class ChatMessageController {

    @Resource
    ChatMessageService chatMessageService;

    @Resource
    ChatSessionService chatSessionService;

    @Login
    @PostMapping("list")
    @ApiOperation(value = "消息列表", notes = "" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "header", dataType = "string")
    })
    public R listChatMessage(@RequestBody ChatMessageForm param,@LoginUser UserEntity userEntity){

        IPage<ChatMessage> chatMessagePage = new Page<>(1,param.getSize());

        QueryWrapper<ChatMessage> wrapper = new QueryWrapper<>();
        wrapper.nested(j->j.nested(i->i.eq("`from`",param.getFrom())
                            .eq("`to`",userEntity.getUserId()))
                .or().nested(i->i.eq("`to`",param.getFrom())
                            .eq("`from`",userEntity.getUserId())))
                .lt("create_date",param.getFromDate())
                .orderBy(true,false,"create_date");

        //todo 这里需要优化不需要查询总条数
        IPage<ChatMessage> resultPage = chatMessageService.page(chatMessagePage,wrapper);

        List<ChatMessage> messageList = resultPage.getRecords();
        Collections.reverse(messageList);

        ArrayList<ChatMessage> batchExecuteList = new ArrayList<>(10);

        // 把没有度过的消息更新成已读过，这里可以使batch的数据更少一些
        for(ChatMessage message:messageList){
            if(!message.getRead()){
                message.setRead(true);
                batchExecuteList.add(message);
            }
        }

        if(batchExecuteList.size() > 0) {
            chatMessageService.updateBatchById(batchExecuteList);
        }


        return R.ok().put("result",resultPage);
    }

    @PostMapping("read")
    @ApiOperation(value = "已读消息", notes = "" )
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
    @PostMapping("conversations")
    @ApiOperation(value = "列出当前人的会话列表", notes = "" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "header", dataType = "string")
    })
    public R listSessions(@LoginUser UserEntity user){
        QueryWrapper<ChatSession> wrapper = new QueryWrapper<>();
        wrapper.eq("`to`",user.getUserId()).orderBy(true,false,"`status`").orderBy(true,false,"update_date");
        List<ChatSession> sessions = chatSessionService.list(wrapper);
        sessions = chatMessageService.batchGetUnreadMessage(user.getUserId(),sessions);
        return R.ok().put("result",sessions);
    }


    /**
     * 未读条数
     * @param user
     * @return
     */
    @Login
    @PostMapping("unread")
    @ApiOperation(value = "未读条数", notes = "" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", paramType = "header", dataType = "string")
    })
    public R countUnread(@LoginUser UserEntity user){
        QueryWrapper<ChatSession> wrapper = new QueryWrapper<>();
        wrapper.eq("`to`",user.getUserId()).eq("`read`",false);
        int unreadCount = chatMessageService.count((Wrapper) wrapper);
        return R.ok().put("result",unreadCount);
    }


    /**
     * 移除一个会话列表
     * @param sessionId
     * @return
     */
    @PostMapping("remove_conversation")
    @ApiOperation(value = "移除一个会话列表", notes = "" )
    public R removeSession(@RequestParam("id") Long sessionId){
        chatSessionService.removeById(sessionId);
        return R.ok();
    }

}
