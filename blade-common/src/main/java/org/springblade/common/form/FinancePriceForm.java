package org.springblade.common.form;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@TableName("tb_finance_price")
@ApiModel("走势图表单")
public class FinancePriceForm {
    /**
     *
     */
    @ApiModelProperty(value = "code",name="code",example = "")
    @NotBlank(message = "code")
    private String code;

}
