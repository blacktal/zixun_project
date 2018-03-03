package com.wcl.zixunproject.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import com.wcl.zixunproject.pojo.Comment;
import com.wcl.zixunproject.service.CommentService;
import com.wcl.zixunproject.service.QuestionService;
import com.wcl.zixunproject.util.EntityType;
import com.wcl.zixunproject.util.HostHolder;
import com.wcl.zixunproject.util.ZixunProjectUtil;

@Controller
public class CommentController {
    
    Logger logger = LoggerFactory.getLogger(CommentController.class);
    
    @Autowired
    CommentService commentService;
    
    @Autowired
    QuestionService questionService;
    
    @Autowired
    HostHolder hostHolder;
    
    @RequestMapping(path = {"/addComment"}, method = {RequestMethod.POST})
    public String addCommentToQuestion(@RequestParam("content") String content,
                             @RequestParam("questionId") int entityId) {
        try {
            Comment comment = new Comment();
            comment.setContent(HtmlUtils.htmlEscape(content));
            comment.setEntityType(EntityType.QUESTION.getValue());
            comment.setEntityId(entityId);
            comment.setCreatedDate(new Date());
            comment.setStatus(0);
            if (hostHolder.getUser() == null) {
                // 未登录用户，按匿名发布处理
                comment.setUserId(ZixunProjectUtil.anonymousUserId);
            }
            // 添加评论和更新评论数这一套应该放在事务中，异步更新评论数的话，就不需要事务了
            comment.setUserId(hostHolder.getUser().getId());
            commentService.addComment(comment);
            // 更新question表中comment数量
            int commentNum = commentService.getCommentNumByEntity(EntityType.QUESTION.getValue(), entityId);
            questionService.updateQuestionCommentNum(entityId, commentNum);
            // 等待异步实现
        } catch (Exception e) {
            logger.error("增加评论失败：" + e.getMessage());
        }
        return "redirect:/question/" + entityId;
    }
}
