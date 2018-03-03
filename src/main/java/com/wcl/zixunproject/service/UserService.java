package com.wcl.zixunproject.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcl.zixunproject.dao.LoginTicketDao;
import com.wcl.zixunproject.dao.UserDao;
import com.wcl.zixunproject.pojo.LoginTicket;
import com.wcl.zixunproject.pojo.User;
import com.wcl.zixunproject.util.ZixunProjectUtil;

@Service
public class UserService {
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    LoginTicketDao ticketDao;
    
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
    
    public Map<String, Object> register(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        // 用户名密码格式校验
        // 用validator更好
        if (username == null | username.isEmpty()) {
            map.put("msg", "用户名不能为空！");
            return map;
        } else if (password == null | password.isEmpty()) {
            map.put("msg", "密码不能为空！");
            return map;
        } else if (username.length() > 20 | username.length() < 3) {
            map.put("msg", "用户名长度在3-20之间！");
            return map;
        } 
        
        if (userDao.getUserByName(username) != null) {
            map.put("msg", "该用户名已经被注册！");
            return map;
        }
        // 新增一个用户    
        User user = new User();
        user.setName(username);
        String salt = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 7);
        user.setPassword(ZixunProjectUtil.MD5("password" + salt));
        user.setSalt(salt);
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        userDao.addUser(user);
        
        // 注册完，为该用户提供一个ticket用于登陆
        String ticket = addTicket(user.getId());
        map.put("ticket", ticket);
        return map;
    }
    
    
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        // 用户名密码格式校验
        // 用validator更好吧
        if (username == null | username.isEmpty()) {
            map.put("msg", "用户名不能为空！");
            return map;
        } else if (password == null | password.isEmpty()) {
            map.put("msg", "密码不能为空！");
            return map;
        } else if (username.length() > 20 | username.length() < 3) {
            map.put("msg", "用户名长度在3-20之间！");
            return map;
        } 
        
        User user = userDao.getUserByName(username);
        if (user == null) {
            map.put("msg", "用户名不存在！");
            return map;
        }
        
        if (!ZixunProjectUtil.MD5("password" + user.getSalt()).equals(user.getPassword())) {
            map.put("msg：", "密码错误！");
            return map;
        } else {
            // 密码正确，往数据库中存入ticket
            String ticket = addTicket(user.getId());
            // 传入controller层，用于加入cookie
            map.put("ticket", ticket);
        }
        
        return map;
    }
    
    /**   
     * @Title: addTicket   
     * @Description: 为用户生成ticket  
     * @param: @param UserId
     * @param: @return      
     * @return: String      
     * @throws   
     */  
    private String addTicket(int UserId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(UserId);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        Date expired = new Date();
        // 设置服务器数据库中ticket过期时间为七天后
        expired.setTime(expired.getTime() + 1000 * 3600 * 24 * 7);
        ticket.setExpired(expired);
        ticketDao.addTicket(ticket);
        return ticket.getTicket();
    }

    public void logout(String ticket) {
        // 登出，将数据库中的ticket状态改为不可用即可
        userDao.updateTicketStatus(ticket, 1);
        
    }

    public User getUserByName(String toName) {
        return userDao.getUserByName(toName);
    }
}
