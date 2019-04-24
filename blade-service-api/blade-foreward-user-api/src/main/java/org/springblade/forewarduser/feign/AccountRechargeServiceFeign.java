package org.springblade.forewarduser.feign;

import org.springblade.common.entity.AccountRecharge;
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
public interface AccountRechargeServiceFeign {

    String ACCOUNT_RECHARGE_API = "/api/accountRecharge";

    @PostMapping(ACCOUNT_RECHARGE_API+"/insert")
    R save(@RequestBody AccountRecharge accountRecharge);



}
