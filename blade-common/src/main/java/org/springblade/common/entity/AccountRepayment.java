package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 分歧还款表(AccountRepayment) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-18 11:59:29
 */
@Data
@TableName("tb_account_repayment")
public class AccountRepayment {

    /***/
    @TableId(type = IdType.INPUT)
    private Long id;
    /***/
    private Long userId;
    private Integer orderId;
    /**
     * 总金额
     */
    private BigDecimal totalAmount;
    /**
     * 未还金额
     */
    private BigDecimal waitAmount;
    /**
     * 已还金额
     */
    private BigDecimal paidAmount;
    /**
     * 当前期数
     */
    private Integer currentPeriod;
    /**
     * 总利息
     */
    private BigDecimal totalInterest;
    /**
     * 待付利息
     */
    private BigDecimal waitInterest;
    /**
     * 已支付利息
     */
    private BigDecimal paidInterest;
    /**
     * 总期数
     */
    private Integer periods;
    /**
     * 最近还款日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date recentRepaymentDate;
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;
}