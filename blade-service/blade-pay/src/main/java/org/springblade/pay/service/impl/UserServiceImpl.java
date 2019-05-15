/**
 * Copyright 2018 首页 http://www.finepetro.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.springblade.pay.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springblade.common.entity.UserEntity;
import org.springblade.pay.mapper.UserDao;
import org.springblade.pay.service.TokenService;
import org.springblade.pay.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author linxiumeng hanbin
 * 用户业务类
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Resource
    private TokenService tokenService;


    public static final String USER_REGISTER_KEY = "market:user:register";


    /**
     * 增加了缓存切面
     *
     * @param id 用户id
     * @return 用户实体
     */
    @Override
   // @Cacheable(value = "user", key = "#id", unless = "#result == null")
    public UserEntity selectByIdFromCache(Long id) {
        return this.getById(id);
    }

    /**
     * 根据id更新数据库并且清空缓存
     *
     * @param userEntity 用户实体
     * @return 更新结果
     */
    @Override
  //  @CacheEvict(value = "user", key = "#userEntity.userId")
    public Boolean updateByIdWithCache(UserEntity userEntity) {
        return this.updateById(userEntity);
    }


}
