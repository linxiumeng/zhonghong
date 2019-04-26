package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.Query;
import io.finepetro.modules.sys.dao.ProviderDao;
import io.finepetro.modules.sys.entity.ProviderEntity;
import io.finepetro.modules.sys.service.ProviderService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("providerService")
public class ProviderServiceImpl extends ServiceImpl<ProviderDao, ProviderEntity> implements ProviderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ProviderEntity> page = this.selectPage(
                new Query<ProviderEntity>(params).getPage(),
                new EntityWrapper<ProviderEntity>()
        );

        return new PageUtils(page);
    }

}
