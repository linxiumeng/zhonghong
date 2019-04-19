package org.springblade.common.form;

import lombok.Data;
import org.springblade.common.entity.PurchaseOrders;
import org.springblade.common.entity.UserEntity;

/**
 * @author hanbin
 */
@Data
public class AccountFinancingPayForm {

    PurchaseOrders purchaseOrders;

    UserEntity user;

    PayForm payForm;


}
