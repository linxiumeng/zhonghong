package org.springblade.common.form;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.entity.Goods;
import org.springblade.common.validation.group.InsertGroup;
import org.springblade.common.validation.group.UpdateGroup;

import javax.validation.constraints.NotBlank;

/**
 * 商品表(Goods) 实体类
 *
 * @author linxiumeng
 * @since 2019-02-13 15:23:01
 */
@Data
@ApiModel("商品新增修改实体表单")
public class GoodsCheckCodeForm extends Goods {

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码",example = "12138")
    @NotBlank(groups = {InsertGroup.class, UpdateGroup.class},message = "验证码不能为空")
    private String code;


}