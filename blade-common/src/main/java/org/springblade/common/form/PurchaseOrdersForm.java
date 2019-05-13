package org.springblade.common.form;

import lombok.Data;
import org.springblade.common.enums.OrdersEnum;

@Data
public class PurchaseOrdersForm extends PageForm {

    OrdersEnum status;

    Integer goodsType;

}

