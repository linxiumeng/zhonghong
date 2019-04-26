package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.Query;
import io.finepetro.modules.sys.dao.RepertoryDao;
import io.finepetro.modules.sys.entity.RepertoryEntity;
import io.finepetro.modules.sys.service.RepertoryService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("repertoryService")
public class RepertoryServiceImpl extends ServiceImpl<RepertoryDao, RepertoryEntity> implements RepertoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RepertoryEntity> page = this.selectPage(
                new Query<RepertoryEntity>(params).getPage(),
                new EntityWrapper<RepertoryEntity>()
        );

        return new PageUtils(page);
    }

}
