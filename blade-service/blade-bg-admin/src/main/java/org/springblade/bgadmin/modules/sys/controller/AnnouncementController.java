package org.springblade.bgadmin.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.bgadmin.modules.sys.entity.AnnouncementEntity;
import org.springblade.bgadmin.modules.sys.service.AnnouncementService;
import org.springblade.common.utils.PageUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 公告
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@RestController
@RequestMapping("sys/announcement")
@Api(tags = "公告", description = " * @author jinzeze")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "")
    //@RequiresPermissions("sys:announcement:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = announcementService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @PostMapping("/info/{id}")
    @ApiOperation(value = "信息", notes = "")
    //@RequiresPermissions("sys:announcement:info")
    public R info(@PathVariable("id") Integer id) {
            AnnouncementEntity announcement = announcementService.getById(id);

        return R.ok().put("announcement", announcement);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存", notes = "")
    //@RequiresPermissions("sys:announcement:save")
    public R save(@RequestBody AnnouncementEntity announcement) {
            announcementService.save(announcement);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改", notes = "")
    //@RequiresPermissions("sys:announcement:update")
    public R update(@RequestBody AnnouncementEntity announcement) {
       //ValidatorUtils.validateEntity(announcement);
            announcementService.updateById(announcement);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "")
    //@RequiresPermissions("sys:announcement:delete")
    public R delete(@RequestBody Integer[] ids) {
            announcementService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
