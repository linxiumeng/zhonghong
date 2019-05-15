package org.springblade.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 提现记录表
 *
 * @author linxiumeng
 * @email lxm@nuoee.com
 * @date 2019-03-16 19:21:05
 */
@TableName("tb_account_withdraw")
@Data
public class AccountWithdraw implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private Integer id;

    private Integer userId;

    private String amount;

    private Integer status;

    private String remark;

    private Date withdrawDate;

    private Date createDate;

    private Date updateDate;

}
