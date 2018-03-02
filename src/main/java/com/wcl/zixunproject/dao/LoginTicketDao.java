package com.wcl.zixunproject.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.wcl.zixunproject.pojo.LoginTicket;

@Mapper
public interface LoginTicketDao {
    
    @Insert(value = {" insert into login_ticket(user_id, ticket, expired, status) "
            + "values(#{userId}, #{ticket}, #{expired}, #{status}) "})
    int addTicket(LoginTicket ticket);
    
    @Select(value = { "select id, user_id, ticket, expired, status from "
            + "login_ticket where user_id = #{userId} and status = 0" })
    LoginTicket selectTicketByUserId(int userId);
    
    @Select(value = { "select id, user_id, ticket, expired, status from "
            + "login_ticket where ticket = #{ticket}" })
    LoginTicket selectTicketByTicket(String ticket);
}
