package org.springblade.bgadmin.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.bgadmin.modules.sys.entity.MessageEntity;
import org.springblade.bgadmin.modules.sys.service.MessageService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 站内信
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/message")
@Api(tags = "余额详情表", description = " * @author jinzeze")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "")
    //@RequiresPermissions("sys:message:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = messageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "")
   // @RequiresPermissions("sys:message:info")
    public R info(@PathVariable("id") Integer id) {
            MessageEntity message = messageService.getById(id);

        return R.ok().put("message", message);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "")
    //@RequiresPermissions("sys:message:save")
    public R save(@RequestBody MessageEntity message) {
            messageService.save(message);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "")
    //@RequiresPermissions("sys:message:update")
    public R update(@RequestBody MessageEntity message) {
        //ValidatorUtils.validateEntity(message);
            messageService.updateById(message);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
   // @RequiresPermissions("sys:message:delete")
    public R delete(@RequestBody Integer[] ids) {
            messageService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
