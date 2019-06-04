package org.springblade.bgadmin.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 融通仓实体类
 * @author hanbin
 */
@Data
@TableName("tb_financing_warehouse")
public class FinancingWarehouseEntity {

    /**
     * id
     */
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 仓单地址
     */
    private String warehouseReceipt;

    /**
     * 金额
     */
    private BigDecimal totalAmount;

    /**
     * 等待还款的金额
     */
    private BigDecimal waitAmount;

    /**
     * 已还的金额
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
     * 等待还款的利息
     */
    private BigDecimal waitInterest;

    /**
     * 已还的利息
     */
    private BigDecimal paidInterest;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 期数
     */
    private Integer periods;

    /**
     * 最近还款日期
     */
    private Date recentRepaymentDate;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;

    /**
     * 审核日期
     */
    private Date verifyDate;

    /**
     * 审批额度
     */
    private BigDecimal verifyAccount;

    /**
     * 年化利率
     */
    private BigDecimal annualInterestRate;

    /**
     * 分期实体
     */
    @TableField(exist = false)
    private List<FinancingWarehouseStepEntity> financingWarehouseStepEntityList;

    @TableField(exist = false)
    private FuserEntity userEntity;

    @TableField(exist = false)
    private int overDateCount;

    @TableField(exist = false)
    private int alreadyPayCount;

}
