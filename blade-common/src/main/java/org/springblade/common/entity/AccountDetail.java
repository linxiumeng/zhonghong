package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class AccountDetail {

    /***/
    @TableId(type = IdType.INPUT)
    private Long id;
    /***/
    private Long userId;
    /**
     * 总资产
     */
    private BigDecimal total;
    /**
     * 使用金额
     */
    private BigDecimal account;
    /**
     * 类型
     */
    private String type;
    /**
     * 备注
     */
    private String remark;
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