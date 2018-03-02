package com.wcl.zixunproject.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wcl.zixunproject.service.QuestionService;
import com.wcl.zixunproject.service.UserService;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class );
    
    @Autowired
    UserService userService;
    
    @Autowired
    QuestionService questionService;
    
    @RequestMapping(path= {"/reglogin"}, method= {RequestMethod.GET, RequestMethod.POST})
    public String RegLogin(Model model, @RequestParam(value = "next", required = false) String next) {
        model.addAttribute("next", next);
        return "login";
    }
    
    @RequestMapping(path= {"/login"}, method= {RequestMethod.GET, RequestMethod.POST})
    public String Login(Model model,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value="rememberme", defaultValue="false") boolean rememberme,
                        @RequestParam(value = "next", required = false) String next,
                        HttpServletResponse response) {
        try{
            Map<String, Object> map = userService.login(username, password);
            if (map.containsKey("ticket")) {
                // 如果map中有ticket，说明后台登陆成功并生成了ticket字段，将其加入response的cookie中
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                if (rememberme) {
                    cookie.setMaxAge(3600 * 24 * 7);
                }
                // 设置cookie可见路径范围
                cookie.setPath("/");
                response.addCookie(cookie);
                if (StringUtils.isNotBlank(next)) {
                    return "redirect:" + next;
                }
                return "redirect:/";
            } else {
                // 错误信息放进model，用于显示在页面上
                model.addAttribute("msg", map.get("msg"));
                // 回到登陆页面
                return "login";
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            model.addAttribute("msg", "服务器错误！");
            return "login";
        }
        
        
    }
    
    @RequestMapping(path= {"/reg"}, method= {RequestMethod.GET, RequestMethod.POST})
    public String Reg(Model model,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam(value="rememberme", defaultValue="false") boolean rememberme,
                      @RequestParam(value = "next", required = false) String next,
                      HttpServletResponse response) {
        try {
            Map<String, Object> map = userService.register(username, password);
            if (map.containsKey("ticket")) {
                // 如果map中有ticket，说明后台登陆成功并生成了ticket字段，将其加入response的cookie中
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if (rememberme) {
                    cookie.setMaxAge(3600 * 24 * 7);
                }
                response.addCookie(cookie);
                if (StringUtils.isNotBlank(next)) {
                    return "redirect:" + next;
                }
                return "redirect:/";
            } else {
                // 错误信息放进model，用于显示在页面上
                model.addAttribute("msg", map.get("msg"));
                // 回到登陆页面
                return "login";
            }
            
        } catch (Exception e) {
            logger.error(e.getMessage());
            model.addAttribute("msg", "服务器错误！");
            return "login";
        }
        
    }
    
    @RequestMapping(path = {"/logout"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String Logout(HttpServletRequest request) {
        // 也可以在参数中直接取@CookieValue("ticket") String ticket
        String ticket = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("ticket"));
            ticket = cookie.getValue();
        }
        if (ticket != null) {
            userService.logout(ticket);
        }
        return "redirect:/";
    }
}
