package org.springblade.bgadmin.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.bgadmin.common.utils.Query;
import org.springblade.bgadmin.modules.sys.dao.PurchaseOrdersDao;
import org.springblade.bgadmin.modules.sys.entity.PurchaseOrdersEntity;
import org.springblade.bgadmin.modules.sys.entity.PurchaseOrdersRepaymentEntity;
import org.springblade.bgadmin.modules.sys.service.PurchaseOrdersService;
import org.springblade.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("purchaseOrdersService")
public class PurchaseOrdersServiceImpl extends ServiceImpl<PurchaseOrdersDao, PurchaseOrdersEntity> implements PurchaseOrdersService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseOrdersEntity> page = this.page(
                new Query<PurchaseOrdersEntity>(params).getPage(),
                new QueryWrapper<PurchaseOrdersEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PurchaseOrdersRepaymentEntity getOrderWithRepayment(Integer id) {
        return baseMapper.selectPurchaseOrderWithRepayment(id);
    }

    @Override
    public IPage listOrderWithRepayment(IPage page, Wrapper wrapper) {

        //wrapper = SqlHelper.fillWrapper(page,wrapper);
        page.setRecords(baseMapper.selectPurchaseOrderWithRepaymentList(page,wrapper));
        return page;
    }


}
