package org.springblade.bgadmin.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分期还款表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@Data
public class AccountRepaymentWithStepEntity extends AccountRepaymentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private FuserEntity userEntity;


    /**
     * 分歧实体
     */
    List<AccountRepaymentStepEntity> accountRepaymentStepEntityList;

    /**
     * 逾期次数
     */
    private Integer overDeadLineCount;

    /**
     * 订单实体
     */
    private PurchaseOrdersEntity ordersEntity;

    /**
     * 已还期数
     */
    private Integer alreadyPayCount;



}
