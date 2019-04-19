package org.springblade.common.form;

import lombok.Data;
import org.springblade.common.entity.PurchaseOrders;
import org.springblade.common.entity.UserEntity;

/**
 * @author hanbin
 */
@Data
public class AccountPayForm {

    PurchaseOrders purchaseOrders;

    UserEntity user;


}
