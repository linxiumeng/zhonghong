package org.springblade.forewarduser.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springblade.common.constant.Constant;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.exception.RRException;
import org.springblade.common.form.LoginForm;
import org.springblade.common.form.RegisterCheckCodeForm;
import org.springblade.common.form.RegisterForm;
import org.springblade.common.form.RegisterWithoutCaptchaForm;
import org.springblade.common.utils.JiguangSmsUtils;
import org.springblade.common.utils.NumberUtils;
import org.springblade.common.utils.R;
import org.springblade.common.utils.RedisUtils;
import org.springblade.forewarduser.service.AccountService;
import org.springblade.forewarduser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 注册接口
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-26 17:27
 */
@RestController
@RequestMapping("/api")
@Api(tags = "注册接口")
public class RegisterController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;


    @Autowired
    private JiguangSmsUtils jiguangSmsUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    RedisTemplate redisTemplate;

    public static final int MAX_RETRY_COUNT = 3;

    public static final String USER_REGISTER_KEY = "market:user:register";

    @PostMapping("getcode")
    @ApiOperation("获取注册短信验证码")
    public R getRegisterCode(@RequestBody RegisterCheckCodeForm registerCheckCodeForm) {

        //获取缓存中的验证码
        String verifyCode = null;
        //这里的redis可能有问题 尝试3次
        int retryCount = 0;
        while (verifyCode == null && retryCount++ < MAX_RETRY_COUNT) {
         //   verifyCode = redisUtils.get(Constant.ANONYMOUS_PREFIX + registerCheckCodeForm.getMarkCode());
            verifyCode = (String) redisTemplate.opsForValue().get(Constant.ANONYMOUS_PREFIX + registerCheckCodeForm.getMarkCode());
        }

        if (StringUtils.isBlank(verifyCode) || !verifyCode.equalsIgnoreCase(registerCheckCodeForm.getCaptcha())) {
            throw new RRException("验证码错误");
        }

        String code = NumberUtils.getRandCode();
        String key = String.format("%s_%s", USER_REGISTER_KEY, registerCheckCodeForm.getMobile());
        boolean flag = jiguangSmsUtils.sendSMSCode(registerCheckCodeForm.getMobile(), code);
        if (flag) {
          //  redisUtils.set(key, code, 5 * 60);
            redisTemplate.opsForValue().set(key,code);
            redisTemplate.expire(key,5*60, TimeUnit.SECONDS);
            return R.ok("获取成功！");
        } else {
            return R.error("短信下发失败！");
        }
    }


    @PostMapping("getcodenocheck")
    @ApiOperation("获取注册短信验证码")
    public R getRegisterCodegetWithoutcaptcha(@RequestBody RegisterWithoutCaptchaForm registerCheckCodeForm) {

        String code = NumberUtils.getRandCode();
        String key = String.format("%s_%s", USER_REGISTER_KEY, registerCheckCodeForm.getMobile());
        boolean flag = jiguangSmsUtils.sendSMSCode(registerCheckCodeForm.getMobile(), code);
        if (flag) {
         //   redisUtils.set(key, code, 5 * 60);
            redisTemplate.opsForValue().set(key,code);
            redisTemplate.expire(key,5*60, TimeUnit.SECONDS);
            return R.ok("获取成功！");
        } else {
            return R.error("短信下发失败！");
        }
    }


    @PostMapping("isregister")
    @ApiOperation("判断是否注册")
    public R isRegister(@RequestBody RegisterCheckCodeForm registerCheckCodeForm) {
        UserEntity user = getUserByMobile(registerCheckCodeForm);

        if (user != null) {
            return R.error(501, "用户已存在！");
        }
        return R.ok("此号码未注册");
    }

    @PostMapping("register")
    @ApiOperation("注册")
    public R register(@RequestBody RegisterForm form) {
        //表单校验
    //    ValidatorUtils.validateEntity(form);
        //调用注册service方法
        return userService.register(form);
    }


    @PostMapping("resetPassword")
    @ApiOperation("重置密码")
    public R resetPassword(@RequestBody RegisterForm form) {
        //表单校验
     //   ValidatorUtils.validateEntity(form);
        UserEntity user = userService.queryByMobile(form.getMobile());
        if (user == null) {
            return R.error(502, "用户不存在！");
        }
        user.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        userService.updateById(user);

        LoginForm form1 = new LoginForm();
        form1.setMobile(form.getMobile());
        form1.setPassword(form.getPassword());
        skipCheck(form1);
        //用户登录
        Map<String, Object> map = userService.login(form1);


        return R.ok(map);
    }



    private void skipCheck(LoginForm form){
        form.setMarkCode("test");
        form.setCaptcha("test");
      //  redisUtils.set(Constant.ANONYMOUS_PREFIX+"test","test",20);
        redisTemplate.opsForValue().set(Constant.ANONYMOUS_PREFIX+"test","test");
        redisTemplate.expire(Constant.ANONYMOUS_PREFIX+"test",20, TimeUnit.SECONDS);
    }


    @PostMapping("isNotRegister")
    @ApiOperation("判断是否注册")
    public R isNotRegister(@RequestBody RegisterCheckCodeForm registerCheckCodeForm) {
        UserEntity user = getUserByMobile(registerCheckCodeForm);

        if (user != null) {
            return R.ok("此号码已注册");
        }
        return R.error(500,"此号码未注册");
    }


    private UserEntity getUserByMobile(RegisterCheckCodeForm registerCheckCodeForm){
        UserEntity user = userService.queryByMobile(registerCheckCodeForm.getMobile());
        //获取缓存中的验证码
        String verifyCode = null;
        int retryCount = 0;

        while(verifyCode == null && retryCount++ < MAX_RETRY_COUNT){
         //   verifyCode = redisUtils.get(Constant.ANONYMOUS_PREFIX+registerCheckCodeForm.getMarkCode());
            verifyCode = (String) redisTemplate.opsForValue().get(Constant.ANONYMOUS_PREFIX+registerCheckCodeForm.getMarkCode());
        }

        if(StringUtils.isBlank(verifyCode) || !verifyCode.equalsIgnoreCase(registerCheckCodeForm.getCaptcha())){
            throw new RRException("图形验证码错误");
        }
        return user;
    }



}
