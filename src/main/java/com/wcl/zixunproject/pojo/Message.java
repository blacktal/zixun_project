package com.wcl.zixunproject.pojo;

import java.util.Date;

public class Message {
    int id;
    String content;
    int fromId;
    int toId;
    Date createdDate;
    int hasRead;//0未读，1已读
    String conversationId;
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
    public int getFromId() {
        return fromId;
    }
    public void setFromId(int fromId) {
        this.fromId = fromId;
    }
    public int getToId() {
        return toId;
    }
    public void setToId(int toId) {
        this.toId = toId;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public int isHasRead() {
        return hasRead;
    }
    public void setHasRead(int hasRead) {
        this.hasRead = hasRead;
    }
    public String getConversationId() {
        return conversationId;
    }
    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
    
}
