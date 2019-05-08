package org.springblade.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.entity.PurchaseOrders;
import org.springblade.common.entity.UserEntity;

import javax.validation.constraints.NotNull;

/**
 * @author hanbin
 * 支付表单
 */
@Data
@ApiModel("支付服务所用的支付表单")
public class AccountFinancingPayForm {

    /**
     * 订单
     */
    @NotNull
    @ApiModelProperty(value = "订单",example = "")
    PurchaseOrders purchaseOrders;

    /**
     * 用户
     */
    @NotNull
    @ApiModelProperty(value = "用户",example = "")
    UserEntity user;

    /**
     * 支付表单
     */
    @NotNull
    @ApiModelProperty(value = "支付表单",example = "")
    PayForm payForm;


}
