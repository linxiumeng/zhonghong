package org.springblade.common.form;

import lombok.Data;
import org.springblade.common.enums.goods.GoodsStatusEnum;

@Data
public class GoodsStatusForm {
    private Long id;
    private GoodsStatusEnum status;
    private String code;
}
