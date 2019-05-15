package org.springblade.bgadmin.modules.sys.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.bgadmin.modules.sys.entity.PurchaseOrdersEntity;
import org.springblade.bgadmin.modules.sys.entity.PurchaseOrdersRepaymentEntity;

import java.util.List;

/**
 * 采购单表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
public interface PurchaseOrdersDao extends BaseMapper<PurchaseOrdersEntity> {

    PurchaseOrdersEntity selectByOrderId(Integer id);

    PurchaseOrdersRepaymentEntity selectByOrderIdWithGoodsEntity(Integer id);

    PurchaseOrdersRepaymentEntity selectPurchaseOrderWithRepayment(Integer id);

    List<PurchaseOrdersRepaymentEntity> selectPurchaseOrderWithRepaymentList(IPage iPage, @Param("ew") Wrapper wrapper);

}
