/*
package org.springblade.pay.feign;

import org.springblade.common.entity.TokenEntity;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

*/
/**
 * @author hanbin
 *//*

@FeignClient(
        value = "blade-foreward-user"
)
public interface TokenServiceFeign {


    String USER_ACCOUNT_TOKEN_API_PREFIX = "/api/user/token";

    */
/**
     * 获取用户详情
     * @param token
     * @return
     *//*

    @GetMapping(USER_ACCOUNT_TOKEN_API_PREFIX + "/detail")
    R<TokenEntity> getTokenEntityByToken(@RequestParam("token") String token);

}
*/
