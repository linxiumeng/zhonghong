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
     * 当前页
     */
    @ApiModelProperty(example = "1",value = "当前页")
    private int page;

    /**
     * 每页条数
     */
    @ApiModelProperty(example = "10",value = "每页条数")
    private int size;

}
