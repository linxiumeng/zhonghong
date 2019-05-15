package org.springblade.forewarduser.controller;


import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomUtils;
import org.springblade.common.annotation.Login;
import org.springblade.common.annotation.LoginUser;
import org.springblade.common.constant.Constant;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.form.LoginForm;
import org.springblade.common.utils.R;
import org.springblade.common.utils.RedisUtils;
import org.springblade.core.log.logger.BladeLogger;
import org.springblade.forewarduser.service.TokenService;
import org.springblade.forewarduser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 登录接口
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/api")
@Api(tags = "登录接口")
public class LoginController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired(required = false)
    private Producer producer;

    @Autowired
    private RedisUtils redisUtils;

    @Resource
    BladeLogger bladeLogger;

    @PostMapping("login")
    @ApiOperation("客户登录")
    public R login(@RequestBody LoginForm form, HttpServletRequest request) {
        //表单校验
      //  ValidatorUtils.validateEntity(form);
/*        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!form.getCaptcha().equalsIgnoreCase(kaptcha)){
            return R.error("验证码不正确");
        }*/


        bladeLogger.info(System.currentTimeMillis() + ""+RandomUtils.nextInt()*100,"login");
        //用户登录
        Map<String, Object> map = userService.login(form);

        return R.ok(map);
    }

    @PostMapping("logout")
    @ApiOperation("退出")
    @Login
    public R logout(@LoginUser UserEntity user) {
        tokenService.expireToken(user.getUserId());
        return R.ok();
    }

    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        setCookieToResponse(response,text);
        //保存到shiro session
        //ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    private void setCookieToResponse(HttpServletResponse response, String verifyCode){
        //生成随机code
        String markCode = System.currentTimeMillis() + "" + new Random().nextInt(9);
        Cookie cookie = new Cookie("markCode", markCode);
        cookie.setPath("/");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);

     //   redisUtils.set("anonymous::"+markCode,verifyCode,60 * 2);
        redisTemplate.opsForValue().set("anonymous::"+markCode,verifyCode);
        redisTemplate.expire("anonymous::"+markCode,60 * 2, TimeUnit.SECONDS);

    }

}
