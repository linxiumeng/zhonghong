package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.mapper.AnnouncementDao;
import org.springblade.bgadmin.modules.sys.entity.AnnouncementEntity;
import org.springblade.bgadmin.modules.sys.service.AnnouncementService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("announcementService")
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementDao, AnnouncementEntity> implements AnnouncementService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AnnouncementEntity> page = this.page(
                new Query<AnnouncementEntity>(params).getPage(),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}
