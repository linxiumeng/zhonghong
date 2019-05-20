package org.springblade.pay.feign;

import org.springblade.common.entity.Account;
import org.springblade.common.form.AccountFinancingPayForm;
import org.springblade.common.form.AccountPayForm;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hanbin
 */
@FeignClient(
        value = "blade-foreward-user"
)
public interface AccountServiceFeign {


    String USER_ACCOUNT_API_PREFIX = "api/user/account";

    /**
     * 根据用户id获取账户信息
     * @param userId
     * @return
     */
    @GetMapping(USER_ACCOUNT_API_PREFIX+"/getByUserId")
    R<Account> getAccountByUserId(@RequestParam Long userId);


    /**
     * 根据实体更新account
     * @param account
     * @return
     */
    @GetMapping(USER_ACCOUNT_API_PREFIX+"/updateBySelective")
    R<Boolean> updateAccountById(@RequestBody Account account);


}


