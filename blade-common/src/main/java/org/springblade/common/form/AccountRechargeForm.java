package org.springblade.common.form;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.validation.group.InsertGroup;
import org.springblade.common.validation.group.UpdateGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 充值记录表(AccountRecharge) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-18 11:51:09
 */
@Data
@ApiModel("充值实体")
public class AccountRechargeForm {

    /**
     * id
     */
    @ApiModelProperty(value = "id",example = "1")
    @NotNull(groups = {UpdateGroup.class},message = "id不能为空")
    @Null(groups = {InsertGroup.class},message = "id需为空")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id",example = "1")
    private Long userId;
    /**
     * 充值金额
     */
 //   @NotNull(groups = {InsertGroup.class})
    @ApiModelProperty(value = "充值金额",example = "999999")
    private Double account;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",example = "林秀栋无敌")
    private String remark;
    /**
     * 审核人
     */
    @ApiModelProperty(value = "审核人",example = "林秀栋")
    @Null(groups = {InsertGroup.class})
    private String verifier;
    /**
     * 审核凭据
     */
    @ApiModelProperty(value = "审核凭证",example = "http://a.img")
    @NotBlank(groups = {InsertGroup.class})
    private String proof;
}