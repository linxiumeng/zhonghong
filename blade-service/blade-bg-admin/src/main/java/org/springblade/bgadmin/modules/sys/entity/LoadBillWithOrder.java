package org.springblade.bgadmin.modules.sys.entity;

import lombok.Data;

/**
 * @author hanbin
 * 提货单实体
 */
@Data
public class LoadBillWithOrder extends LoadBill {


    private PurchaseOrdersEntity purchaseOrdersEntity;

}
