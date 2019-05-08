package org.springblade.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 付款表单
 */
@Data
@ApiModel("支付表单")
public class PayForm {
    /**
     * 订单编号
     */
    @NotNull(message = "订单编号不能为空")
    @ApiModelProperty(value = "订单编号",example = "1111")
    private Integer orderNo;
    /**
     * 融资金额
     */
    @ApiModelProperty(value = "融资金额",example = "60121321")
    @NotNull(message = "融资金额不能为空")
    private BigDecimal financing;
    /**
     * 分期数
     */
    @ApiModelProperty(value = "分期数",example = "12")
    @NotNull(message = "分期数不能为空")
    private Integer stages;
}
