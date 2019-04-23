package org.springblade.pay.feign;

import org.springblade.common.entity.PurchaseOrders;
import org.springblade.common.form.AccountFinancingPayForm;
import org.springblade.common.form.AccountPayForm;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hanbin
 */
@FeignClient(
        value = "blade-order"
)
public interface PurchaseOrdersServiceFeign {


    String ORDER_API_PREFIX = "api/purchase-order";


    /**
     * 采购单详情
     * @param ordersId
     * @return
     */
    @RequestMapping("/detail")
    R<PurchaseOrders> getPurchaseOrdersDetail(@RequestParam("id")Long ordersId);

}


