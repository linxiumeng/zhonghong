package org.springblade.bgadmin.modules.sys.form;

import io.finepetro.modules.sys.enums.OrdersEnum;
import io.finepetro.modules.sys.enums.PaymentEnum;
import io.finepetro.modules.sys.enums.RepaymentOrderEnum;
import lombok.Data;

/**
 * @author hanbin
 * 订单表单
 */
@Data
public class PurchaseOrderForm extends BaseForm{


    private Integer id;

    private Integer status;

    private OrdersEnum ordersStatus;

    private PaymentEnum paymentStatus;

    private RepaymentOrderEnum repaymentOrderStatus;

    private Integer[] orderStatusArr;


}

