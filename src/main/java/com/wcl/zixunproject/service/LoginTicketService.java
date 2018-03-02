package com.wcl.zixunproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.wcl.zixunproject.dao.LoginTicketDao;
import com.wcl.zixunproject.pojo.LoginTicket;

@Service
public class LoginTicketService {

    @Autowired
    LoginTicketDao ticketDao;
    
    public int addTicket(LoginTicket ticket) {
        return ticketDao.addTicket(ticket);
    }
    
    public LoginTicket selectTicketByUserId(int userId) {
        return ticketDao.selectTicketByUserId(userId);
    }
    
    public LoginTicket selectTicketByTicket(String ticket) {
        return ticketDao.selectTicketByTicket(ticket);
    }
}
