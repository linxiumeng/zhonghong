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

package org.springblade.order.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springblade.common.annotation.Login;
import org.springblade.common.constant.Constant;
import org.springblade.common.constant.FeignResultCodeConstant;
import org.springblade.common.entity.TokenEntity;
import org.springblade.common.exception.RRException;
import org.springblade.common.utils.R;
import org.springblade.order.feign.TokenServiceFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * 权限(Token)验证
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:38
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private TokenServiceFeign tokenService;

    @Resource
    ObjectMapper objectMapper;

    public static final String USER_KEY = "userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        } else {
            return true;
        }

        if (annotation == null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }

        //token为空
        if (StringUtils.isBlank(token)) {
            throw new RRException("token不能为空");
        }

        //查询token信息
        org.springblade.core.tool.api.R<TokenEntity> r = tokenService.getTokenEntityByToken(token);
        TokenEntity tokenEntity = r.getData();

        //判断feign的返回
        if (r.getCode() == FeignResultCodeConstant.ENTITY_NOT_EXISTS || tokenEntity == null || tokenEntity.getExpireTime() == null ||  tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            //throw new RRException("token失效，请重新登录");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(R.ok().put("code", Constant.TOKEN_EXPIRE_CODE).put("msg",Constant.TOKEN_EXPIRE_DESC)));
            writer.flush();
            writer.close();
            return false;
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(USER_KEY, tokenEntity.getUserId());

        return true;
    }
}
