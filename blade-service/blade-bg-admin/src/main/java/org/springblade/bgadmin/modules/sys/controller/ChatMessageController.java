package org.springblade.bgadmin.modules.sys.controller;

import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.R;
import io.finepetro.common.validator.ValidatorUtils;
import io.finepetro.modules.sys.entity.ChatMessageEntity;
import io.finepetro.modules.sys.service.ChatMessageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 聊天记录
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/chatmessage")
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:chatmessage:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = chatMessageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:chatmessage:info")
    public R info(@PathVariable("id") Integer id) {
            ChatMessageEntity chatMessage = chatMessageService.selectById(id);

        return R.ok().put("chatMessage", chatMessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:chatmessage:save")
    public R save(@RequestBody ChatMessageEntity chatMessage) {
            chatMessageService.insert(chatMessage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:chatmessage:update")
    public R update(@RequestBody ChatMessageEntity chatMessage) {
        ValidatorUtils.validateEntity(chatMessage);
            chatMessageService.updateAllColumnById(chatMessage);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:chatmessage:delete")
    public R delete(@RequestBody Integer[] ids) {
            chatMessageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
