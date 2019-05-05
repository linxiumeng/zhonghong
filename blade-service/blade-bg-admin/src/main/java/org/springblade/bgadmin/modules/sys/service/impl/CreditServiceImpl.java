package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.mapper.CreditDao;
import org.springblade.bgadmin.modules.sys.entity.CreditEntity;
import org.springblade.bgadmin.modules.sys.service.CreditService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("creditService")
public class CreditServiceImpl extends ServiceImpl<CreditDao, CreditEntity> implements CreditService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CreditEntity> page = this.page(
                new Query<CreditEntity>(params).getPage(),
                new QueryWrapper<>()
        );

        return new PageUtils(page);
    }

}
