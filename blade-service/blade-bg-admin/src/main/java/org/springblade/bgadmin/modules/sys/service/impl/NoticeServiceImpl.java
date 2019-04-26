package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.Query;
import io.finepetro.modules.sys.dao.NoticeDao;
import io.finepetro.modules.sys.entity.NoticeEntity;
import io.finepetro.modules.sys.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, NoticeEntity> implements NoticeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NoticeEntity> page = this.selectPage(
                new Query<NoticeEntity>(params).getPage(),
                new EntityWrapper<NoticeEntity>()
        );

        return new PageUtils(page);
    }

}
