package org.springblade.bgadmin.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 采购单 用户 表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
@Data
public class PurchaseOrdersRepaymentEntity extends PurchaseOrdersEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private AccountRepaymentWithStepEntity accountRepaymentEntity;

    /**
     * 第一次支付的金额
     */
    private BigDecimal firstPaidAccount;

    private LoadBill loadBillEntity;

}
