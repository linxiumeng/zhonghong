package org.springblade.bgadmin.modules.sys.form;

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
    private Integer period;


    /**
     * 审核日期
     */
    private Date verifyDate;

    private int page;

    private int size;

    /**
     * 逾期标示位
     */
    private Integer overDateStatus;

    /**
     * 通过标示位
     */
    private Integer passStatus;

    /**
     * 审核数量
     */
    private BigDecimal verifyAccount;

    /**
     * 年化利率
     */
    private BigDecimal annualInterestRate;


    private Date startDate;

    private Date endDate;

    private String keywords;

}
