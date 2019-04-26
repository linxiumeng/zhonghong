package org.springblade.bgadmin.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.AnnouncementEntity;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 公告
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
public interface AnnouncementService extends IService<AnnouncementEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

