package com.wcl.zixunproject.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcl.zixunproject.dao.CommentDao;
import com.wcl.zixunproject.pojo.Comment;

@Service
public class CommentService {
    
    Logger logger = LoggerFactory.getLogger(CommentService.class);
    
    @Autowired
    CommentDao commentDao;
    
    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }
    
    public List<Comment> getCommentByEntity(int entityType, int entityId) {
        return commentDao.getCommentByEntity(entityType, entityId);
    }
    
    public int deleteComment(int id) {
        // 状态置为1，表示评论不可见，即已删除
        return commentDao.updateCommentStatus(id, 1);
    }
    
    public int getCommentNumByEntity(int entityType, int entityId) {
        return commentDao.getCommentNumByEntity(entityType, entityId);
    }

}
