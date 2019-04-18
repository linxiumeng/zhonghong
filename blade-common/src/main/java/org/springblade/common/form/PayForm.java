package org.springblade.common.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 付款表单
 */
@Data
public class PayForm {
    private Integer orderNo;
    /**融资金额*/
    private BigDecimal financing;
    /**分期数*/
    private int stages;
}
