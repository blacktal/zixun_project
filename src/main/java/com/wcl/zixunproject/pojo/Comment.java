package com.wcl.zixunproject.pojo;

import java.util.Date;

public class Comment {
    int id;
    String content;
    int userId;
    int entityType;
    int entityId;
    int status;
    Date createdDate;
    
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getEntityType() {
        return entityType;
    }
    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }
    public int getEntityId() {
        return entityId;
    }
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
