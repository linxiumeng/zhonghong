package org.springblade.forewarduser.feign;

import org.springblade.common.entity.AccountRepayment;
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


    @PostMapping
    public Long saveWithId(@RequestBody AccountRepayment accountRepayment);

    

}
