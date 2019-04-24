package org.springblade.forewarduser.feign;

import org.springblade.common.entity.AccountRepaymentStep;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * accountdetail Feign接口类
 *
 * @author Chill
 */
@RestController
public class AccountRepaymentStepServiceFeignImpl implements AccountRepaymentStepServiceFeign {


    @Override
    @PostMapping("/ab")
    public R batchSave(@RequestBody List<AccountRepaymentStep> accountRepaymentSteps){
        return null;
    }

    

}
