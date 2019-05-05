package org.springblade.bgadmin.modules.sys.form;

import lombok.Data;
import org.springblade.bgadmin.modules.sys.enums.OrdersEnum;
import org.springblade.bgadmin.modules.sys.enums.PaymentEnum;
import org.springblade.bgadmin.modules.sys.enums.RepaymentOrderEnum;

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

