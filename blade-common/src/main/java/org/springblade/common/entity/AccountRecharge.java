package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class AccountRecharge {

    /***/
    @TableId(type = IdType.INPUT)
    private Long id;
    /***/
    private Long userId;
    /**
     * 充值类型 0是充值，1是提现
     */
    private Integer type;
    /**
     * 充值金额
     */
    private Double account;
    /**
     * 充值状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 审核人
     */
    private String verifier;
    /**
     * 审核凭据
     */
    private String proof;
    /**
     * 验证时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date verifiyDate;
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createDate;
    /***/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date updateDate;
}