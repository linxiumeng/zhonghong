package org.springblade.bgadmin.modules.sys.form;

import lombok.Data;
import org.springblade.bgadmin.modules.sys.enums.GoodsFormEnum;

/**
 * @author hanbin
 * 用户表单
 */
@Data
public class GoodsForm extends BaseForm{


    /**
     * 商品id
     */
    Integer goodsId;

    /**
     * 商品状态 审核
     */
    Integer goodsStatus;

    /**
     * 商品上架状态
     */
    Integer auditStatus;

    GoodsFormEnum goodsFormStatus;


    String goodsType;




}

