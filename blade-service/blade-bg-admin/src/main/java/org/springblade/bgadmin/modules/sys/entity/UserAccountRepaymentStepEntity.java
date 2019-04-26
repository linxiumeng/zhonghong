package org.springblade.bgadmin.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 分期还款详情表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@Data
public class UserAccountRepaymentStepEntity extends AccountRepaymentStepEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户实体
     */
    private FuserEntity userEntity;

    /**
     * 账户订单实体
     */
    private AccountRepaymentEntity accountRepaymentEntity;

}
