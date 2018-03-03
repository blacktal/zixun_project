package com.wcl.zixunproject.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wcl.zixunproject.pojo.Comment;
import com.wcl.zixunproject.pojo.Question;
import com.wcl.zixunproject.service.CommentService;
import com.wcl.zixunproject.service.QuestionService;
import com.wcl.zixunproject.service.UserService;
import com.wcl.zixunproject.util.EntityType;
import com.wcl.zixunproject.util.HostHolder;
import com.wcl.zixunproject.util.ViewObject;
import com.wcl.zixunproject.util.ZixunProjectUtil;

@Controller
public class QuestionController {
    
    Logger logger = LoggerFactory.getLogger(QuestionController.class);
    
    @Autowired
    QuestionService questionService;
    
    @Autowired
    CommentService commentService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    HostHolder hostHolder;
    
    @RequestMapping(path = {"/question/{questionId}"}, method = RequestMethod.GET)
    public String questionDetail(Model model,
                                 @PathVariable("questionId") int questionId) {
        Question question = questionService.getQuestionById(questionId);
        List<Comment> commentList = commentService.getCommentByEntity(EntityType.QUESTION.getValue(), questionId);
        // 只获取comment列表还不够，每条comment的发表人名字，头像，url都要显示到
        List<ViewObject> vos = new ArrayList<>();
        for (Comment comment : commentList) {
            ViewObject vo = new ViewObject();
            vo.set("comment", comment);
            // 严格来说还应该验证一下user是否存在，不存在则匿名显示该评论
            vo.set("user", userService.getUserById(comment.getUserId()));
            vos.add(vo);
        }
        model.addAttribute("question", question);
        model.addAttribute("comments", vos);
        return "detail";
    }
    
    // 按跟前端的约定，返回Json串格式
    @RequestMapping(path = {"/question/add"}, method = RequestMethod.POST)
    @ResponseBody
    public String addQuestion(@RequestParam("title") String title,
                              @RequestParam("content") String content) {
        try {
            Question question = new Question();
            question.setTitle(title);
            question.setContent(content);
            question.setCreatedDate(new Date());
            // 如果用户没有登陆，发送约定的999代码，前端弹出弹框提醒登陆
            if (hostHolder.getUser() == null) {
                return ZixunProjectUtil.getJSONString(999, "用户未登陆");
            }
            question.setUserId(hostHolder.getUser().getId());
            questionService.addQuestion(question);
            // 返回Json
            return ZixunProjectUtil.getJSONString(0);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } return ZixunProjectUtil.getJSONString(1, "添加问题失败");
    }
}
