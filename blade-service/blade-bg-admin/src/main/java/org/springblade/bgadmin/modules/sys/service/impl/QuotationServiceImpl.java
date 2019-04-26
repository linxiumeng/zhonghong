package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.Query;
import io.finepetro.modules.sys.dao.QuotationDao;
import io.finepetro.modules.sys.entity.QuotationEntity;
import io.finepetro.modules.sys.service.QuotationService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("quotationService")
public class QuotationServiceImpl extends ServiceImpl<QuotationDao, QuotationEntity> implements QuotationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QuotationEntity> page = this.selectPage(
                new Query<QuotationEntity>(params).getPage(),
                new EntityWrapper<QuotationEntity>()
        );

        return new PageUtils(page);
    }

}
