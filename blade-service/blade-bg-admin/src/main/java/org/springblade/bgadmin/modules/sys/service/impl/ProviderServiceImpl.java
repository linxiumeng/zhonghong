package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.dao.ProviderDao;
import org.springblade.bgadmin.modules.sys.entity.ProviderEntity;
import org.springblade.bgadmin.modules.sys.service.ProviderService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("providerService")
public class ProviderServiceImpl extends ServiceImpl<ProviderDao, ProviderEntity> implements ProviderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProviderEntity> page = this.page(
                new Query<ProviderEntity>(params).getPage(),
                new QueryWrapper<ProviderEntity>()
        );

        return new PageUtils(page);
    }

}
