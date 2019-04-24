package org.springblade.forewarduser.feign;

import org.springblade.common.entity.AccountRepayment;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * accountdetail Feign接口类
 *
 * @author Chill
 */
@FeignClient(
        value = "blade-pay"
)
public interface AccountRepaymentServiceFeign {

    String ACCOUNT_REPAYMENT_API = "/api/AccountRepayment";

    @PostMapping(ACCOUNT_REPAYMENT_API+"/insertWithId")
    public R<AccountRepayment> saveWithId(@RequestBody AccountRepayment accountRepayment);

    

}
