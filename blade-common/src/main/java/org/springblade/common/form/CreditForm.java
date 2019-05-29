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
    @ApiModelProperty(value = "授信等级",example = "A")
    private String creditLevel;

    /**
     * 当前授信额度
     */
    @ApiModelProperty(value = "当前授信额度",example = "100000")
    private BigDecimal creditLimit;
    /**
     * 最高授信额度
     */
    @ApiModelProperty(value = "最高授信额度",example = "12.36")
    private BigDecimal creditHigh;
    /**
     * 单笔授信额度
     */
    @ApiModelProperty(value = "单笔授信额度",example = "23.32")
    private BigDecimal creditUnit;


}
