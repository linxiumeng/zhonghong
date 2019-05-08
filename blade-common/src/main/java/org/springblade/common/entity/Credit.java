package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springblade.common.validation.group.InsertGroup;
import org.springblade.common.validation.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 授信表(Credit) 实体类
 *
 * @author linxiumeng
 * @since 2019-03-07 16:15:37
 */
@Data
@TableName("tb_credit")
public class Credit {

    /***/
    @TableId(type = IdType.INPUT)
    @Null(groups = {InsertGroup.class})
    @NotNull(groups = {UpdateGroup.class})
    private Integer id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 借款申请表
     */
    private String loanApplication;
    /**
     * 财务报表
     */
    private String financialStatement;
    /**
     * 缴税证明
     */
    private String taxCertification;
    /**
     * 银行流水
     */
    private String accountStatement;
    /**
     * 授信额度证明
     */
    private String certificateOfCreditLine;
    /**
     * 其他证明
     */
    private String orther;
}