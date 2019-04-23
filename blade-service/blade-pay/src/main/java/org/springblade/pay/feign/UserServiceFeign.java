package org.springblade.pay.feign;

import org.springblade.common.entity.UserEntity;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hanbin
 */
@FeignClient(
        value = "blade-user"
)
public interface UserServiceFeign {


    String USER_API_PREFIX = "/api/fuser";

    /**
     * 获取用户详情
     * @param id
     * @return
     */
    @GetMapping(USER_API_PREFIX + "/detail")
    R<UserEntity> getUserById(@RequestParam("userId") Long id);

}
