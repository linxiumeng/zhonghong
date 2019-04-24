package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 余额表(Account) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-14 17:30:41
 */
@Data
@TableName("tb_account")
public class Account {

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
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;

}