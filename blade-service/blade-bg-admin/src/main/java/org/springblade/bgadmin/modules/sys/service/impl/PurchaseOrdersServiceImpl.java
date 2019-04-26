package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.finepetro.common.utils.PageUtils;
import io.finepetro.common.utils.Query;
import io.finepetro.modules.sys.dao.PurchaseOrdersDao;
import io.finepetro.modules.sys.entity.PurchaseOrdersEntity;
import io.finepetro.modules.sys.entity.PurchaseOrdersRepaymentEntity;
import io.finepetro.modules.sys.service.PurchaseOrdersService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("purchaseOrdersService")
public class PurchaseOrdersServiceImpl extends ServiceImpl<PurchaseOrdersDao, PurchaseOrdersEntity> implements PurchaseOrdersService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PurchaseOrdersEntity> page = this.selectPage(
                new Query<PurchaseOrdersEntity>(params).getPage(),
                new EntityWrapper<PurchaseOrdersEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PurchaseOrdersRepaymentEntity getOrderWithRepayment(Integer id) {
        return baseMapper.selectPurchaseOrderWithRepayment(id);
    }

    @Override
    public Page listOrderWithRepayment(Page page, Wrapper wrapper) {

        wrapper = SqlHelper.fillWrapper(page,wrapper);
        page.setRecords(baseMapper.selectPurchaseOrderWithRepaymentList(page,wrapper));
        return page;
    }


}
