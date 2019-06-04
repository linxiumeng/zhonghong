package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 融通仓分期实体类
 * @author hanbin
 */
@Data
@TableName("tb_financing_warehouse_step")
public class FinancingWarehouseStepEntity {

    /**
     * id
     */
    private Long id;

    /**
     * 仓单申请id
     */
    private Long financingId;

    /**
     * 基本金额
     */
    private BigDecimal account;

    /**
     * 已还金额
     */
    private BigDecimal paidAccount;

    /**
     * 利息
     */
    private BigDecimal interest;

    /**
     * 利率
     */
    private BigDecimal interestRate;

    /**
     * 已还利息
     */
    private BigDecimal paidInterest;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 当前期数
     */
    private Integer period;

    /**
     * 还款日期
     */
    private Date periodDate;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;



}
