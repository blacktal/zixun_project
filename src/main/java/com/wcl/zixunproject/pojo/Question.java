package com.wcl.zixunproject.pojo;

import java.util.Date;

public class Question {
    
    int id;
    String title;
    String content;
    Date createdDate;
    int commentNum;
    int userId;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public int getCommentNum() {
        return commentNum;
    }
    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

}
