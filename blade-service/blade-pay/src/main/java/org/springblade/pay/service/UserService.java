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

package org.springblade.pay.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.form.ChangePassword;
import org.springblade.common.form.LoginForm;
import org.springblade.common.form.RegisterForm;
import org.springblade.common.utils.R;

import java.util.Map;

/**
 * 用户
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:06
 */
public interface UserService extends IService<UserEntity> {




    /**
     * 从缓存中读取用户实体
     * @param id 用户id
     * @return 用户实体
     */
    UserEntity selectByIdFromCache(Long id);

    /**
     * 更新
     * @param userEntity 用户实体
     * @return 更新成功
     */
    Boolean updateByIdWithCache(UserEntity userEntity);


}
