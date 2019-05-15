package org.springblade.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("授信表实体类")
public class Credit {

    /**
     * 自增id
     */
    @TableId(type = IdType.INPUT)
    @Null(groups = {InsertGroup.class})
    @NotNull(groups = {UpdateGroup.class})
    @ApiModelProperty(value = "自增id",name="id",example = "1")
    private Integer id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id",name="userId",example = "1")
    private Long userId;
    /**
     * 借款申请表
     */
    @ApiModelProperty(value = "借款申请表",name="loanApplication",example = "")
    private String loanApplication;
    /**
     * 财务报表
     */
    @ApiModelProperty(value = "财务报表",name="financialStatement",example = "")
    private String financialStatement;
    /**
     * 缴税证明
     */
    @ApiModelProperty(value = "缴税证明",name="taxCertification",example = "")
    private String taxCertification;
    /**
     * 银行流水
     */
    @ApiModelProperty(value = "银行流水",name="accountStatement",example = "")
    private String accountStatement;
    /**
     * 授信额度证明
     */
    @ApiModelProperty(value = "授信额度证明",name="certificateOfCreditLine",example = "")
    private String certificateOfCreditLine;
    /**
     * 其他证明
     */
    @ApiModelProperty(value = "其他证明",name="orther",example = "")
    private String orther;
}