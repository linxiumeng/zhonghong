package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 充值记录表(AccountRecharge) 实体类
 *
 * @author linxiumeng
 * @since 2019-03-12 11:39:03
 */
@Data
@TableName("tb_account_recharge")
@ApiModel("充值记录表实体类")
public class AccountRecharge {

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
     * 充值类型 0是充值，1是提现
     */
    @ApiModelProperty(value = "充值类型 0是充值，1是提现",name="type",example = "1")
    private Integer type;
    /**
     * 充值金额
     */
    @ApiModelProperty(value = "充值金额",name="account",example = "10.00")
    private Double account;
    /**
     * 充值状态
     */
    @ApiModelProperty(value = "充值状态",name="status",example = "1")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",name="remark",example = "这笔钱是用来娶媳妇的")
    private String remark;
    /**
     * 审核人
     */
    @ApiModelProperty(value = "审核人",name="verifier",example = "帅帅的泽泽")
    private String verifier;
    /**
     * 审核凭据
     */
    @ApiModelProperty(value = "审核凭据",name="proof",example = "")
    private String proof;
    /**
     * 验证时间
     */
    @ApiModelProperty(value = "验证时间",name="verifiyDate",example = "2019-12-01 12:23")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date verifiyDate;
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