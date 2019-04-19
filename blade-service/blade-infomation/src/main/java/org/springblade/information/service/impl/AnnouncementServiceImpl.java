package org.springblade.information.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.Announcement;
import org.springblade.information.mapper.AnnouncementDao;
import org.springblade.information.service.AnnouncementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * (Announcement)表服务实现类
 *
 * @author linxiumeng
 * @since 2019-03-12 10:46:58
 */
@Service("announcementService")
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementDao, Announcement> implements AnnouncementService {
    @Resource
    private AnnouncementDao announcementDao;

    @Override
    public IPage listAnnouncements(Page<Announcement> page) {
        QueryWrapper<Announcement> wrapper = new QueryWrapper<>();
        wrapper.orderBy(true,false,"creat_time");
        IPage<Announcement> resultPage = this.page(page,wrapper);
        return resultPage;
    }
}