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
 * 余额详情表(AccountDetail) 实体类
 *
 * @author linxiumeng
 * @since 2019-03-12 11:48:32
 */
@Data
@TableName("tb_account_detail")
@ApiModel("余额表详情表实体类")
public class AccountDetail {

    /**
     * 主键 自增
     */
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "自增id",name="id")
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id",name="userId",example = "1")
    private Long userId;
    /**
     * 总资产
     */
    @ApiModelProperty(value = "总资产",name="total",example = "10")
    private BigDecimal total;
    /**
     * 使用金额
     */
    @ApiModelProperty(value = "使用金额",name="account",example = "5")
    private BigDecimal account;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型",name="type",example = "string")
    private String type;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",name="remark",example = "泽泽很帅")
    private String remark;
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