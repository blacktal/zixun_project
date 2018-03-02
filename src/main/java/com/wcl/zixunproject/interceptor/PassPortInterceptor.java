package com.wcl.zixunproject.interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wcl.zixunproject.pojo.LoginTicket;
import com.wcl.zixunproject.pojo.User;
import com.wcl.zixunproject.service.LoginTicketService;
import com.wcl.zixunproject.service.UserService;
import com.wcl.zixunproject.util.HostHolder;

@Component
public class PassPortInterceptor implements HandlerInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(PassPortInterceptor.class);
    
    @Autowired
    LoginTicketService ticketService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    HostHolder hostholder;

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // 所有都处理完成后，删除此次请求中的hostHoder
        hostholder.clear();
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        logger.info("postHandle " + arg0.getRequestURL());
        System.out.println(arg3 == null);
        System.out.println(hostholder.getUser());
        // 在渲染页面之前，把当前用户从hostholder中复制到ModelandView中，以便在页面显示
        if (arg3 != null && hostholder.getUser() != null) {
            arg3.addObject("currentUser", hostholder.getUser());
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        logger.info("preHandle " + arg0.getRequestURL());
        // 取到请求中的ticket
        String ticket = null;
        if (arg0.getCookies() != null) {
            for (Cookie cookie : arg0.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
            // 到数据库中验证该ticket是否可用
            if (ticket != null) {
                LoginTicket ticketInDB = ticketService.selectTicketByTicket(ticket);
                if (ticketInDB != null && ticketInDB.getStatus() == 0 && ticketInDB.getExpired().after(new Date())) {
                    User user = userService.getUserById(ticketInDB.getUserId());
                    // 将当前用户取出，放入ThreadLocal变量中暂存
                    hostholder.setUser(user);
                    return true;
                }
            }
        }
        return true;
    }

}
