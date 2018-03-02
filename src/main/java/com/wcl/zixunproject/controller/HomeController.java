package com.wcl.zixunproject.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wcl.zixunproject.pojo.Question;
import com.wcl.zixunproject.service.QuestionService;
import com.wcl.zixunproject.service.UserService;
import com.wcl.zixunproject.util.ViewObject;

@Controller
public class HomeController {
    
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class );
    
    @Autowired
    UserService userService;
    
    @Autowired
    QuestionService questionService;
    
    @RequestMapping(path = {"/","/index"}, method = RequestMethod.GET)
    public String Home(Model model) {
        List<Question> questionList = questionService.getLatestQuestions(0, 0, 10);
        List<ViewObject> vos = new ArrayList<>();
        for (Question question : questionList) {
            ViewObject vo = new ViewObject();
            vo.set("question", question);
            vo.set("user", userService.getUserById(question.getUserId()));
            vos.add(vo);
        }
        model.addAttribute("vos", vos);
        return "index";
    }
    
    @RequestMapping(path = {"/user/{userId}"}, method = RequestMethod.GET)
    public String UserHome(Model model, @PathVariable("userId") int userId) {
        List<Question> questionList = questionService.getLatestQuestions(userId, 0, 10);
        List<ViewObject> vos = new ArrayList<>();
        for (Question question : questionList) {
            ViewObject vo = new ViewObject();
            vo.set("question", question);
            vo.set("user", userService.getUserById(question.getUserId()));
            vos.add(vo);
        }
        model.addAttribute("vos", vos);
        return "index";
    }
}
