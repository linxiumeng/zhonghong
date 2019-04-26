package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.dao.NoticeDao;
import org.springblade.bgadmin.modules.sys.entity.NoticeEntity;
import org.springblade.bgadmin.modules.sys.service.NoticeService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, NoticeEntity> implements NoticeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<NoticeEntity> page = this.page(
                new Query<NoticeEntity>(params).getPage(),
                new QueryWrapper<NoticeEntity>()
        );

        return new PageUtils(page);
    }

}
