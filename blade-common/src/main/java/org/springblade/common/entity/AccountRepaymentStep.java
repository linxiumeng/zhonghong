package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 分期还款详情表(AccountRepaymentStep) 实体类
 *
 * @author linxiumeng
 * @since 2019-03-13 13:33:47
 */
@Data
@TableName("tb_account_repayment_step")
public class AccountRepaymentStep {

    /***/
    @TableId(type = IdType.INPUT)
    private Long id;
    /***/
    private Long repaymentId;
    /**
     * 金额
     */
    private BigDecimal account;
    /**
     * 利息
     */
    private BigDecimal interest;
    /**
     * 已还金额
     */
    private BigDecimal paidAccount;
    /**
     * 已还利息
     */
    private BigDecimal paidInterest;
    /**
     * 状态 0:未还款 1:已还款 2:已逾期
     */
    private Integer status;
    /**
     * 期数
     */
    private Integer preiod;
    /**
     * 还款日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date preiodDate;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;
}