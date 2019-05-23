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

package org.springblade.forewarduser.service.impl;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.common.constant.Constant;
import org.springblade.common.entity.Account;
import org.springblade.common.entity.TokenEntity;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.enums.ErrorEnum;
import org.springblade.common.enums.UserLoginStatusEnum;
import org.springblade.common.exception.RRException;
import org.springblade.common.form.ChangePassword;
import org.springblade.common.form.LoginForm;
import org.springblade.common.form.RegisterForm;
import org.springblade.common.utils.R;
import org.springblade.common.utils.RedisUtils;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.forewarduser.mapper.UserAccountDao;
import org.springblade.forewarduser.mapper.UserDao;
import org.springblade.forewarduser.service.AccountService;
import org.springblade.forewarduser.service.TokenService;
import org.springblade.forewarduser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author linxiumeng hanbin
 * 用户业务类
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private TokenService tokenService;

    @Resource
    AccountService accountService;

    @Resource
    RedisUtils redisUtils;

    @Resource
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private UserAccountDao userAccountDao;
    public static final String USER_REGISTER_KEY = "market:user:register";

    public static final int MAX_RETRY_COUNT = 3;

    /**
     * 这里处理回滚异常
     *
     * @param form
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public R register(RegisterForm form) {

        UserEntity user = this.queryByMobile(form.getMobile());
        if (user != null) {
            return R.error(501, "用户已存在！");
        }

        String key = String.format("%s_%s", USER_REGISTER_KEY, form.getMobile());

        String code = null;

        int retryCount = 0;

        while(code == null && retryCount++ < MAX_RETRY_COUNT){
         //   code = redisUtils.get(key);
            code = (String) redisTemplate.opsForValue().get(key);
        }

        if (!(code != null && form.getCode().equals(code))) {
            return R.error("验证码错误");
        }
        user = new UserEntity();
        user.setMobile(form.getMobile());
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        this.save(user);
        LoginForm form1 = new LoginForm();
        form1.setMobile(form.getMobile());
        form1.setPassword(form.getPassword());
        skipCheck(form1);
        //用户登录
        Map<String, Object> map = this.login(form1);
        Account account = new Account();
        BigDecimal bd = new BigDecimal("0.0");
        account.setAccount(bd);
        account.setUserId(((Long) map.get("userId")));
        account.setCashFund(bd);
        account.setCreditHigh(bd);
        account.setCreditLimit(bd);
        account.setCreditUnit(bd);
        account.setFreezeAmount(bd);
        account.setTotal(bd);
        accountService.save(account);
        return R.ok(map);
    }

    public void skipCheck(LoginForm form) {
        form.setMarkCode("test");
        form.setCaptcha("test");
       // redisUtils.set(Constant.ANONYMOUS_PREFIX + "test", "test", 20);
        redisTemplate.opsForValue().set(Constant.ANONYMOUS_PREFIX+"test","test");
        redisTemplate.expire(Constant.ANONYMOUS_PREFIX+"test",20, TimeUnit.SECONDS);

    }

    /**
     * 根据手机查询用户实体
     *
     * @param mobile 手机号
     * @return 用户实体
     */
    @Override
    public UserEntity queryByMobile(String mobile) {
        UserEntity userEntity = new UserEntity();
        userEntity.setMobile(mobile);
        return baseMapper.selectOne(new QueryWrapper<UserEntity>(userEntity));
    }

    /**
     * 登录
     *
     * @param form 登录表单
     * @return 相关结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> login(LoginForm form) {
        UserEntity user = queryByMobile(form.getMobile());
        if (user == null) {
            throw new RRException("用户不存在", 502);
        }
        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))) {
            throw new RRException("密码错误", 502);
        }

        if (UserLoginStatusEnum.values()[user.getLoginStatus()] == UserLoginStatusEnum.FREEZE) {
            throw new RRException("用户已被冻结", 502);
        }

        //增加重试机制
        int retryCount = 0;
        String code = null;

        while(code == null && retryCount++ < MAX_RETRY_COUNT){
            //获取缓存中的验证码
            // code = redisUtils.get(Constant.ANONYMOUS_PREFIX + form.getMarkCode());
            code = (String) redisTemplate.opsForValue().get(Constant.ANONYMOUS_PREFIX + form.getMarkCode());
        }

        logger.info("login phone is : {} , markCode is : {} , fromRedisCode is : {}  , form's captcha is : {} ", form.getMobile(), form.getMarkCode(), code, form.getCaptcha());

        if (StringUtils.isBlank(code) || !code.equalsIgnoreCase(form.getCaptcha())) {
            throw new RRException("验证码错误");
        }

        //获取登录token
        TokenEntity tokenEntity = tokenService.createToken(user.getUserId());

        Map<String, Object> map = new HashMap<>(2);
        map.put("token", tokenEntity.getToken());
        map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());
        map.put("userId", user.getUserId());
        map.put("type", user.getType());
     //   map.put("fileToken",createSTSOSSToken());
        return map;
    }


    /**
     * 缓存清除
     *
     * @param pw   密码
     * @param user 用户
     * @return 更新成功行数
     */
    @Override
    //  @CacheEvict(value = "user", key = "#user.userId")
    public Integer changePw(ChangePassword pw, UserEntity user) {
        //   Assert.isNull(user, ErrorEnum.用户不存在.getDesc());
        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(pw.getOldPassword()))) {
            throw new RRException(ErrorEnum.用户密码错误.getDesc(), ErrorEnum.用户密码错误.getCode());
        }
        user.setPassword(DigestUtils.sha256Hex(pw.getNewPassword()));
        return userDao.updateById(user);
    }


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
