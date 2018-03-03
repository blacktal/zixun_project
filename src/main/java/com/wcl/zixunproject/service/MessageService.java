package com.wcl.zixunproject.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcl.zixunproject.dao.MessageDao;
import com.wcl.zixunproject.pojo.Message;

@Service
public class MessageService {
    Logger logger = LoggerFactory.getLogger(MessageService.class);
    
    @Autowired
    MessageDao messageDao;
    
    public int addMessage(Message message) {
        return messageDao.addMessage(message);
    }
    
    public List<Message> getMessageListByUserId(int userId, int offset, int limit) {
        return messageDao.getMessageListByUserId(userId, offset, limit);
    }
}
