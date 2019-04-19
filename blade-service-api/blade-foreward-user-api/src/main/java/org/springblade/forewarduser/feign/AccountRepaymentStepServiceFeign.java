package org.springblade.forewarduser.feign;

import org.springblade.common.entity.AccountRepaymentStep;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * accountdetail Feign接口类
 *
 * @author Chill
 */
@FeignClient(
        value = "blade-pay"
)
public interface AccountRepaymentStepServiceFeign {


    @PostMapping
    boolean batchSave(@RequestBody List<AccountRepaymentStep> accountRepaymentSteps);

    

}
