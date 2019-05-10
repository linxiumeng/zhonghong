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
 * 分歧还款表(AccountRepayment) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-18 11:59:29
 */
@Data
@TableName("tb_account_repayment")
@ApiModel("分歧还款表实体类")
public class AccountRepayment {

    /**
     * 自增id
     */
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "自增id",name="id",example = "1")
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id",name="userId",example = "1")
    private Long userId;
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id",name="orderId",example = "131313213")
    private Integer orderId;
    /**
     * 总金额
     */
    @ApiModelProperty(value = "总金额",name="totalAmount",example = "10")
    private BigDecimal totalAmount;
    /**
     * 未还金额
     */
    @ApiModelProperty(value = "未还金额",name="waitAmount",example = "20")
    private BigDecimal waitAmount;
    /**
     * 已还金额
     */
    @ApiModelProperty(value = "已还金额",name="paidAmount",example = "50")
    private BigDecimal paidAmount;
    /**
     * 当前期数
     */
    @ApiModelProperty(value = "当前期数",name="currentPeriod",example = "63")
    private Integer currentPeriod;
    /**
     * 总利息
     */
    @ApiModelProperty(value = "总利息",name="totalInterest",example = "365")
    private BigDecimal totalInterest;
    /**
     * 待付利息
     */
    @ApiModelProperty(value = "待付利息",name="waitInterest",example = "25")
    private BigDecimal waitInterest;
    /**
     * 已支付利息
     */
    @ApiModelProperty(value = "已支付利息",name="paidInterest",example = "321")
    private BigDecimal paidInterest;
    /**
     * 总期数
     */
    @ApiModelProperty(value = "总期数",name="periods",example = "12")
    private Integer periods;
    /**
     * 最近还款日期
     */
    @ApiModelProperty(value = "最近还款日期",name="recentRepaymentDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date recentRepaymentDate;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "用户id",name="createDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "用户id",name="updateDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;
}