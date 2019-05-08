package org.springblade.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.entity.Quotation;
import org.springblade.common.validation.group.InsertGroup;

import javax.validation.constraints.NotBlank;

/**
 * @author hanbin
 * 报价单表单
 */
@Data
@ApiModel("报价单表单")
public class QuotationForm extends Quotation {

    /**
     * 验证码
     */
    @NotBlank(groups = {InsertGroup.class},message = "验证码不能为空")
    @ApiModelProperty(value = "手机验证码",example = "12138")
    private String code;

    /**
     * 分页
     */
    private int page;

    /**
     * 分页
     */
    private int size;

}
