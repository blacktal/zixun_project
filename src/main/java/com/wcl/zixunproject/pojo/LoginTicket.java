package com.wcl.zixunproject.pojo;

import java.util.Date;

public class LoginTicket {
    
    int id;
    String ticket;
    Date expired;
    int userId;
    int status;//0有效，1无效
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    public Date getExpired() {
        return expired;
    }
    public void setExpired(Date expired) {
        this.expired = expired;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

}
