package org.springblade.common.respond;


import lombok.Data;

import java.math.BigDecimal;


/**
 * @author hanbin
 * 账户数据传输
 */
@Data
public class AccountDto {

    /**
     * 账户id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 可用余额
     */
    private BigDecimal account;
    /**
     * 账户总资产
     */
    private BigDecimal total;
    /**
     * 冻结金额
     */
    private BigDecimal freezeAmount;
    /**
     * 保证金
     */
    private BigDecimal cashFund;
    /**
     * 当前授信额度
     */
    private BigDecimal creditLimit;
    /**
     * 最高授信额度
     */
    private BigDecimal creditHigh;
    /**
     * 单笔授信额度
     */
    private BigDecimal creditUnit;


}
