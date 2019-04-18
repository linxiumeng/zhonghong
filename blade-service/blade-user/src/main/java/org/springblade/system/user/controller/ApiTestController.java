package org.springblade.system.user.controller;

import org.springblade.common.utils.JiguangSmsUtils;
import org.springblade.common.utils.RedisUtils;
import org.springblade.core.tool.api.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hanbin
 */
@RestController
@RequestMapping("/")
public class ApiTestController {


    @Resource
    RedisUtils redisUtils;

    @Resource
    JiguangSmsUtils jiguangSmsUtils;



    @GetMapping("/test-redis")
    public R testRedis() {


        //jiguangSmsUtils.sendSMSCode("18342360411", "12345");

        redisUtils.set("a","a");
        System.out.println(redisUtils.get("a"));

        return R.status(true);
    }

    @GetMapping("/test-send-message")
    public R testSendMessage() {


        jiguangSmsUtils.sendSMSCode("18342360411", "12345");

        return R.status(true);
    }

}
