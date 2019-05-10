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
 * 余额表(Account) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-14 17:30:41
 */
@Data
@TableName("tb_account")
@ApiModel("余额表实体类")
public class Account {

    /**
     * id
     */
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "自增id",name="id",example = "一个一个往上增")
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id",name="userId",example = "6")
    private Long userId;
    /**
     * 可用余额
     */
    @ApiModelProperty(value = "可用余额",name="account",example = "5698754265.36")
    private BigDecimal account;
    /**
     * 账户总资产
     */
    @ApiModelProperty(value = "账户总资产",name="total",example = "56987542653.36")
    private BigDecimal total;
    /**
     * 冻结金额
     */
    @ApiModelProperty(value = "冻结金额",name="freezeAmount",example = "0.23")
    private BigDecimal freezeAmount;
    /**
     * 保证金
     */
    @ApiModelProperty(value = "保证金",name="cashFund",example = "500")
    private BigDecimal cashFund;
    /**
     * 当前授信额度
     */
    @ApiModelProperty(value = "当前授信额度",name="creditLimit",example = "14.36")
    private BigDecimal creditLimit;
    /**
     * 最高授信额度
     */
    @ApiModelProperty(value = "最高授信额度",name="creditHigh",example = "36.36")
    private BigDecimal creditHigh;
    /**
     * 单笔授信额度
     */
    @ApiModelProperty(value = "单笔授信额度",name="creditUnit",example = "20.36")
    private BigDecimal creditUnit;
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