package org.springblade.bgadmin.modules.sys.controller;

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
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sys:announcement:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = announcementService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sys:announcement:info")
    public R info(@PathVariable("id") Integer id) {
            AnnouncementEntity announcement = announcementService.getById(id);

        return R.ok().put("announcement", announcement);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sys:announcement:save")
    public R save(@RequestBody AnnouncementEntity announcement) {
            announcementService.save(announcement);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sys:announcement:update")
    public R update(@RequestBody AnnouncementEntity announcement) {
       //ValidatorUtils.validateEntity(announcement);
            announcementService.updateById(announcement);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sys:announcement:delete")
    public R delete(@RequestBody Integer[] ids) {
            announcementService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
