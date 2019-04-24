package org.springblade.forewarduser.feign;

import org.springblade.common.entity.AccountRepayment;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * accountdetail Feign接口类
 *
 * @author Chill
 */
@RestController
public class AccountRepaymentServiceFeignImpl implements AccountRepaymentServiceFeign {


    @Override
    @PostMapping("/abb")
    public R<AccountRepayment> saveWithId(@RequestBody AccountRepayment accountRepayment){
        return null;
    }



}
