package org.springblade.common.utils;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author hanbin
 * 短信校验 抽离出工具类 避免多个地方重复使用
 */
@Component
public class SmsCheckUtils {

    public static final String USER_REGISTER_KEY = "market:user:register";


    @Resource
    RedisUtils redisUtils;

    public boolean check(String mobile, String verifyCode) {
        String key = String.format("%s_%s", USER_REGISTER_KEY, mobile);
        String code = redisUtils.get(key);
        return code != null && code.equals(verifyCode);
    }
}
