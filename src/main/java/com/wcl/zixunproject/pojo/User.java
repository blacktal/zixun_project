package com.wcl.zixunproject.pojo;

public class User {
    
    int id;
    String name;
    String password;
    String headUrl;
    String salt;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getHeadUrl() {
        return headUrl;
    }
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }

}
