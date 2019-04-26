package org.springblade.bgadmin.modules.sys.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.bgadmin.modules.sys.entity.PurchaseOrdersEntity;
import org.springblade.bgadmin.modules.sys.entity.PurchaseOrdersRepaymentEntity;
import org.springblade.common.utils.PageUtils;

import java.util.Map;

/**
 * 采购单表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
public interface PurchaseOrdersService extends IService<PurchaseOrdersEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PurchaseOrdersRepaymentEntity getOrderWithRepayment(Integer id);

    IPage listOrderWithRepayment(IPage page, Wrapper wrapper);

}

