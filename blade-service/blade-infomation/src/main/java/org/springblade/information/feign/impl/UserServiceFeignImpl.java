package org.springblade.information.feign.impl;

import org.springblade.common.entity.UserEntity;
import org.springblade.core.tool.api.R;
import org.springblade.information.feign.UserServiceFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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
    @GetMapping(USER_API_PREFIX + "/fallback/detail1")
    public R<Collection<UserEntity>> batchGetUserByIds(Collection<Long> ids) {
        R r = R.status(true);
        r.setData(null);
        return r;
    }
}
