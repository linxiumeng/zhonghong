package org.springblade.information.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.Announcement;

/**
 * (Announcement)表服务接口
 *
 * @author linxiumeng
 * @since 2019-03-12 10:46:58
 */
public interface AnnouncementService extends IService<Announcement> {

    /**
     * 展示公告
     * @param page
     * @return
     */
    IPage listAnnouncements(Page<Announcement> page);
    
}