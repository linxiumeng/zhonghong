package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.dao.QuotationDao;
import org.springblade.bgadmin.modules.sys.entity.QuotationEntity;
import org.springblade.bgadmin.modules.sys.service.QuotationService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("quotationService")
public class QuotationServiceImpl extends ServiceImpl<QuotationDao, QuotationEntity> implements QuotationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QuotationEntity> page = this.page(
                new Query<QuotationEntity>(params).getPage(),
                new QueryWrapper<QuotationEntity>()
        );

        return new PageUtils(page);
    }

}
