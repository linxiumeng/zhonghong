package org.springblade.forewarduser.feign;

import org.springblade.common.entity.AccountRecharge;
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


    @PostMapping("/aa")
    boolean save(@RequestBody AccountRecharge accountRecharge);



}
