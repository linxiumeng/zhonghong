package org.springblade.forewarduser.feign;

import org.springblade.common.entity.AccountDetail;
import org.springblade.common.entity.AccountWithdraw;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * accountdetail Feign接口类
 *
 * @author Chill
 */
@FeignClient(
        value = "blade-pay"
)
public interface AccountWithdrawServiceFeign {


    @PostMapping("/AccountWithdraw/createAccountWithdraw")
    R<Boolean> save(AccountWithdraw accountWithdraw);




}
