package org.springblade.order.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springblade.common.annotation.HasPermission;
import org.springblade.common.annotation.Login;
import org.springblade.common.constant.FeignResultCodeConstant;
import org.springblade.common.entity.UserEntity;
import org.springblade.common.enums.PermissionCodeEnum;
import org.springblade.common.utils.R;
import org.springblade.order.feign.UserServiceFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * @author hanbin
 * 采购商 供应商 权限控制拦截器
 */
@Component
public class PermissionCheckInterceptor extends HandlerInterceptorAdapter {

    @Resource
    UserServiceFeign userService;

    /**
     * 成员
     */
    @Resource
    ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HasPermission hasPermissionAnnotation;
        Login loginAnnotation;
        if (handler instanceof HandlerMethod) {
            hasPermissionAnnotation = ((HandlerMethod) handler).getMethodAnnotation(HasPermission.class);
            loginAnnotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        } else {
            return true;
        }

        if (hasPermissionAnnotation == null) {
            return true;
        } else if (hasPermissionAnnotation != null && loginAnnotation == null) {
            return false;
        }

        //获取用户ID
        Object object = request.getAttribute(AuthorizationInterceptor.USER_KEY);
        if (object == null) {
            return false;
        }

        org.springblade.core.tool.api.R<UserEntity> r = userService.getUserById((Long) object);

        if(r.getCode() != FeignResultCodeConstant.ENTITY_NOT_EXISTS) {

            UserEntity user = r.getData();

            if (user != null) {

                if (hasPermissionAnnotation != null) {

                    if (hasPermissionAnnotation.needVerifyCredit() || hasPermissionAnnotation.needVerifyUser()) {
                        //判断用户是否验证通过
                        if (user.getStatus() != 3) {
                            //获取writer
                            response.setCharacterEncoding("utf-8");
                            response.setContentType("application/json; charset=utf-8");
                            PrintWriter writer = response.getWriter();

                            writer.print(objectMapper.writeValueAsString(R.ok().put("result", PermissionCodeEnum.TWO.getStatus()).put("msg", PermissionCodeEnum.TWO.getDesc())));
                            writer.flush();
                            writer.close();
                            return false;
                        }
                        //判断用是是否授信
                        if (hasPermissionAnnotation.needVerifyCredit()) {
                            if (user.getCreditStatus() != 3) {
                                //获取writer
                                response.setCharacterEncoding("utf-8");
                                response.setContentType("application/json; charset=utf-8");
                                PrintWriter writer = response.getWriter();
                                writer.print(objectMapper.writeValueAsString(R.ok().put("result", PermissionCodeEnum.SIX.getStatus()).put("msg", PermissionCodeEnum.SIX.getDesc())));
                                writer.flush();
                                writer.close();
                                return false;
                            }
                        }

                    }


                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);


    }
}
