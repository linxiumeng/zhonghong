package org.springblade.order.feign;

import org.springblade.common.form.AccountFinancingPayForm;
import org.springblade.common.form.AccountPayForm;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author hanbin
 */
@FeignClient(
        value = "blade-foreward-user"
)
public interface AccountServiceFeign {


    String USER_ACCOUNT_API_PREFIX = "api/user/account";

    /**
     * 支付
     * @param accountnPayForm
     * @return
     */
    @PostMapping(USER_ACCOUNT_API_PREFIX + "/pay")
    R pay(@RequestBody AccountPayForm accountnPayForm);

    /**
     * 融资支付
     * @param accountFinancingPayForm
     * @return
     */
    @PostMapping(USER_ACCOUNT_API_PREFIX + "/financingPay")
    R financingPay(@RequestBody AccountFinancingPayForm accountFinancingPayForm);


}


