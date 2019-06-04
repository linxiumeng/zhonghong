package org.springblade.common.form;

import lombok.Data;
import org.springblade.common.validation.group.UpdateGroup;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 融通仓表单类
 * @author hanbin
 */
@Data
public class FinancingWarehouseForm {

    /**
     * id
     */
    @NotNull(message = "id不能为null",groups = UpdateGroup.class)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 仓单地址
     */
    private String warehouseReceipt;

    /**
     * 金额
     */
    private BigDecimal totalAmount;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 期数
     */
    private Integer periods;


    /**
     * 审核日期
     */
    private Date verifyDate;

    private int page;

    private int size;

}
