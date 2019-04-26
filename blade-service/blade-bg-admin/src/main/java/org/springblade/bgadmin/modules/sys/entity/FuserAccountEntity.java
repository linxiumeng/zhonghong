package org.springblade.bgadmin.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:04
 */
@Data
public class FuserAccountEntity extends FuserEntity implements Serializable {


    /**
     * 逾期次数
     */
    private Integer overDeadLineCount = 0;

    /**
     * 商家产品数量
     */
    private Integer productCount = 0;

    /**
     * 账户实体
     */
    private AccountEntity accountEntity;

    /**
     * 申请书实体
     */
    private AttestEntity attestEntity;

    /**
     * 授信实体
     */
    private CreditEntity creditEntity;

}
