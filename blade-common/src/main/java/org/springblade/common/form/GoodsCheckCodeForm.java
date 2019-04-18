package org.springblade.common.form;


import lombok.Data;
import org.springblade.common.entity.Goods;

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
        private String code;





}