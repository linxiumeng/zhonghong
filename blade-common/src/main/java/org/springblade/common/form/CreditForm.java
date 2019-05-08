package org.springblade.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.entity.Credit;

import java.math.BigDecimal;

/**
 * @author hanbin
 * 授信表单
 */
@Data
@ApiModel("授信表单")
public class CreditForm extends Credit {

    /**
     * 授信等级
     */
    @ApiModelProperty(value = "",example = "")
    private String creditLevel;

    /**
     * 当前授信额度
     */
    private BigDecimal creditLimit;
    /**
     * 最高授信额度
     */
    private BigDecimal creditHigh;
    /**
     * 单笔授信额度
     */
    private BigDecimal creditUnit;


}
