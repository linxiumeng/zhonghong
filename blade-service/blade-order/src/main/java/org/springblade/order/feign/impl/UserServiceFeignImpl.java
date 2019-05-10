package org.springblade.order.feign.impl;

import org.springblade.common.entity.UserEntity;
import org.springblade.core.tool.api.R;
import org.springblade.order.feign.UserServiceFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * @author hanbin
 */
@RestController
public class UserServiceFeignImpl implements UserServiceFeign {


    @Override
    @GetMapping(USER_API_PREFIX + "/fallback/detail")
    public R<UserEntity> getUserById(Long id) {
        R r = R.status(true);
        r.setData(null);
        return r;
    }

    @Override
    @GetMapping(USER_API_PREFIX + "/fallback/batchGetByIds")
    public R<Collection<UserEntity>> batchGetUserByIds(Collection<Long> ids) {
        R r = R.status(true);
        r.setData(null);
        return r;
    }
}
