package org.springblade.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.enums.GoodsStatusEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hanbin
 * 修改商品状态表单
 */
@Data
@ApiModel("修改商品状态表单")
public class GoodsStatusForm {
    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空")
    @ApiModelProperty(value = "商品id",example = "1")
    private Long id;

    /**
     * 商品状态
     */
    @NotNull(message = "商品状态不能为空")
    @ApiModelProperty(value = "商品状态",example = "0")
    private GoodsStatusEnum status;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码",example = "12138")
    private int code;
}
