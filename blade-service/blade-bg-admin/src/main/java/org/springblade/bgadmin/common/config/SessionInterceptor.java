package org.springblade.bgadmin.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.finepetro.common.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author hanbin
 * session拦截器
 */
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {

    @Resource
    ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();

        //  System.out.println(cookies);

        String sessionID = null;

        for (Cookie cookie : cookies) {
            if ("JSESSIONID".equalsIgnoreCase(cookie.getName())) {
                sessionID = cookie.getValue();
            }
        }

        SessionKey key = new WebSessionKey(sessionID, request, response);
        try {
            Session se = SecurityUtils.getSecurityManager().getSession(key);
            Object obj = se.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
            if (obj == null) {
                PrintWriter writer = response.getWriter();
                writer.println(objectMapper.writeValueAsString(R.ok().put("code", 3000)));
                writer.flush();
                return false;
            }
        } catch (UnknownSessionException e) {
            PrintWriter writer = response.getWriter();
            writer.println(objectMapper.writeValueAsString(R.ok().put("code", 3000)));
            writer.flush();
            return false;
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
