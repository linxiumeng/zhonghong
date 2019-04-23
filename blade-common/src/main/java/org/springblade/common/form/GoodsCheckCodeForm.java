package org.springblade.common.form;


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
public class GoodsCheckCodeForm extends Goods {

        /**
         * 验证码
         */
        @NotBlank(groups = {InsertGroup.class, UpdateGroup.class})
        private String code;





}