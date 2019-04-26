package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.dao.RepertoryDao;
import org.springblade.bgadmin.modules.sys.entity.RepertoryEntity;
import org.springblade.bgadmin.modules.sys.service.RepertoryService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("repertoryService")
public class RepertoryServiceImpl extends ServiceImpl<RepertoryDao, RepertoryEntity> implements RepertoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RepertoryEntity> page = this.page(
                new Query<RepertoryEntity>(params).getPage(),
                new QueryWrapper<RepertoryEntity>()
        );

        return new PageUtils(page);
    }

}
