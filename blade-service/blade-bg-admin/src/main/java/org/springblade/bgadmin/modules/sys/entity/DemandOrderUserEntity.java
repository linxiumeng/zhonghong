package org.springblade.bgadmin.modules.sys.entity;

import lombok.Data;

/**
 * @author hanbin
 * 需求符合查询实体类
 */
@Data
public class DemandOrderUserEntity extends DemandEntity {

    private FuserEntity userEntity;

    private PurchaseOrdersEntity purchaseOrdersEntity;

    /**
     * 报价单个数
     */
    private Integer quotationCount;
}
