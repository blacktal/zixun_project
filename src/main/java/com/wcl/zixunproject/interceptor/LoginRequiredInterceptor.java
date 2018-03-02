package com.wcl.zixunproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wcl.zixunproject.util.HostHolder;

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginRequiredInterceptor.class);
    
    @Autowired
    HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("preHandler " + request.getRequestURL());
        System.out.println("sessionId: " + request.getRequestedSessionId() + "| URI: " + request.getRequestURI() + " | servletPath:" + request.getServletPath());
        // 检验HostHolder中是否有当前登陆用户
        if (hostHolder.getUser() == null) {
            // 如果是未登录状态，设置重定位的路径至登陆页面
            response.sendRedirect("/reglogin?next=" + request.getRequestURI());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        
    }

}
