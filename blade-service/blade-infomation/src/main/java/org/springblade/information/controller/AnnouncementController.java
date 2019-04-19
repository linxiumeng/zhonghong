package org.springblade.information.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springblade.common.entity.Announcement;
import org.springblade.common.form.PageForm;
import org.springblade.common.utils.R;
import org.springblade.information.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author linxiumeng
 * @since 2019-03-12 10:46:58
 */
@Api(tags = "(Announcement)表操作控制器",description = " * @author linxiumeng")
@RestController
@RequestMapping("api/announcement")
public class AnnouncementController {
    private AnnouncementService announcementService;
    
    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    /**
     * 获取公告详情
     * @return
     */
    @PostMapping("detail")
    @ApiOperation(value = "获取公告详情", response = AnnouncementService.class)
    public R getAnnouncementById(@RequestParam("id")Integer id) {

        if(id == null){
            return R.error("id 为 null");
        }
        Announcement announcement = announcementService.getById(id);
        return R.ok().put("row",announcement);
    }


    /**
     * 获取公告列表
     * @return
     */
    @PostMapping("list")
    @ApiOperation(value = "获取公告列表", response = AnnouncementService.class)
    public R listAnnouncementByPage(@RequestBody PageForm pageForm) {
        Page<Announcement> page = new Page<>(pageForm.getPage(),pageForm.getSize());
        IPage<Announcement> resultPage = announcementService.listAnnouncements(page);
        return R.ok().put("result",resultPage);
    }

}