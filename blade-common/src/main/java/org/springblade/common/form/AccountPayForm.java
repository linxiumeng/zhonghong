package org.springblade.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.entity.PurchaseOrders;
import org.springblade.common.entity.UserEntity;

import javax.validation.constraints.NotNull;

/**
 * @author hanbin
 */
@Data
@ApiModel("支付服务所用的支付表单,这里与Financing暂时不做区别")
public class AccountPayForm {

    /**
     * 订单
     */
    @NotNull(message = "订单不能为空")
    @ApiModelProperty(value = "订单",example = "")
    PurchaseOrders purchaseOrders;

    /**
     * 用户
     */
    @NotNull(message = "用户不能为空")
    @ApiModelProperty(value = "用户",example = "")
    UserEntity user;


}
