package org.springblade.common.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.common.entity.Goods;
import org.springblade.common.entity.GoodsTypeEntity;
import org.springblade.common.enums.GoodsStatusEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hanbin
 * 修改商品状态表单
 */
@Data
public class GoodsTypeForm extends GoodsTypeEntity {


}
