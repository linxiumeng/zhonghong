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

package org.springblade.bgadmin.modules.sys.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springblade.bgadmin.modules.sys.entity.SysMenuEntity;
import org.springblade.bgadmin.modules.sys.entity.SysUserEntity;
import org.springblade.bgadmin.modules.sys.service.SysMenuService;
import org.springblade.bgadmin.modules.sys.shiro.ShiroUtils;
import org.springblade.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * 登录相关
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月10日 下午1:15:31
 */
@Controller
@Api(tags = "登录相关", description = " * @author jinzeze")
public class SysLoginController {
	@Autowired
	private Producer producer;

	@Autowired
	SysMenuService sysMenuService;

	@RequestMapping("captcha.jpg")
	@ApiOperation(value = "获取验证码", notes = "")
	public void captcha(HttpServletResponse response)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
	}

	/**
	 * 登录
	 */
	@ResponseBody
	@PostMapping(value = "/sys/login")
	@ApiOperation(value = "登录", notes = "")
	public R login(String username, String password, String captcha) {
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equalsIgnoreCase(kaptcha)){
			return R.error("验证码不正确");
		}
		List<SysMenuEntity> menuList = null;
		try{
			Subject subject = ShiroUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password,true);
			subject.login(token);
			SysUserEntity user = (SysUserEntity) subject.getPrincipal();
			if(user != null) {
				menuList = sysMenuService.getUserMenuList(user.getUserId());
			}
		}catch (UnknownAccountException e) {
			return R.error(e.getMessage());
		}catch (IncorrectCredentialsException e) {
			return R.error("账号或密码不正确");
		}catch (LockedAccountException e) {
			return R.error("账号已被锁定,请联系管理员");
		}catch (AuthenticationException e) {
			return R.error("账户验证失败");
		} catch (Exception e){
			System.out.println(e.getMessage());
		}

		return R.ok().put("result",menuList);
	}

	/**
	 * 退出
	 */
	@ResponseBody
	@PostMapping(value = "logout")
	@ApiOperation(value = "退出", notes = "")
	public R logout() {
		ShiroUtils.logout();
		return R.ok();
	}


	/**
	 * 返回session没有的接口
	 */
	@ResponseBody
	@GetMapping(value = "nonesession")
	public R notsession() {
		return R.error(3000,"session失效或者您未登陆");
	}
}
