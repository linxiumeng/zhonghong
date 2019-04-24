package org.springblade.forewarduser.feign;

import org.springblade.common.entity.AccountDetail;
import org.springblade.common.entity.AccountRecharge;
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
public class AccountRechargeServiceFeignImpl implements AccountRechargeServiceFeign{


    @Override
    @PostMapping("/abbb")
    public R save(@RequestBody AccountRecharge accountRecharge){
        return null;
    }



}
