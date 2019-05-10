package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("分期还款详情表实体类")
public class AccountRepaymentStep {

    /**
     * 自增id
     */
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "自增id",name="id",example = "1")
    private Long id;
    /**
     * 还款id
     */
    @ApiModelProperty(value = "还款id",name="repaymentId",example = "10")
    private Long repaymentId;
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额",name="account",example = "10")
    private BigDecimal account;
    /**
     * 利息
     */
    @ApiModelProperty(value = "利息",name="interest",example = "103.3")
    private BigDecimal interest;
    /**
     * 已还金额
     */
    @ApiModelProperty(value = "已还金额",name="paidAccount",example = "13")
    private BigDecimal paidAccount;
    /**
     * 已还利息
     */
    @ApiModelProperty(value = "已还利息",name="paidInterest",example = "123")
    private BigDecimal paidInterest;
    /**
     * 状态 0:未还款 1:已还款 2:已逾期
     */
    @ApiModelProperty(value = "状态 0:未还款 1:已还款 2:已逾期",name="status",example = "1")
    private Integer status;
    /**
     * 期数
     */
    @ApiModelProperty(value = "期数",name="preiod",example = "11")
    private Integer preiod;
    /**
     * 还款日期
     */
    @ApiModelProperty(value = "还款日期",name="preiodDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date preiodDate;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",name="createDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间",name="updateDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;
}