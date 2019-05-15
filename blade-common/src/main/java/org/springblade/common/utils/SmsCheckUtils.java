package org.springblade.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author hanbin
 * 短信校验 抽离出工具类 避免多个地方重复使用
 */
@Component
public class SmsCheckUtils {

    @Autowired
    RedisTemplate redisTemplate;

    Logger logger = LoggerFactory.getLogger(SmsCheckUtils.class);

    public static final String USER_REGISTER_KEY = "market:user:register";

    public static final int MAX_RETRY_COUNT = 3;

    @Autowired(required = false)
    RedisUtils redisUtils;

    public boolean check(String mobile, String verifyCode) {
        String key = String.format("%s_%s", USER_REGISTER_KEY, mobile);
        String code = null;
        int retryCount = 0;
        while(code == null && retryCount++ < MAX_RETRY_COUNT){
          //  code = redisUtils.get(key);
            code = (String) redisTemplate.opsForValue().get(key);
        }

        logger.info("smsCheckUtils check   mobile is {} , fromRedisCode is {} , verfiyCode is {} ",mobile,code,verifyCode);
        return code != null && code.equals(verifyCode);
    }
}
