package org.springblade.information.feign;

import org.springblade.common.entity.UserEntity;
import org.springblade.core.tool.api.R;
import org.springblade.information.feign.impl.UserServiceFeignImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author hanbin
 */
@FeignClient(
        value = "blade-foreward-user",fallback = UserServiceFeignImpl.class
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

    /**
     * 批量获取
     * @param ids
     * @return
     */
    @GetMapping(USER_API_PREFIX + "/batchGetByIds")
    R<Collection<UserEntity>> batchGetUserByIds(@RequestParam("userIds") Collection<Long> ids);


}
