package com.wcl.zixunproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wcl.zixunproject.pojo.Question;
import com.wcl.zixunproject.service.QuestionService;

@Controller
public class QuestionController {
    
    Logger logger = LoggerFactory.getLogger(QuestionController.class);
    
    @Autowired
    QuestionService questionService;
    
    @RequestMapping(path = {"/question/{questionId}"})
    public String QuestionDetail(Model model,
                                 @PathVariable("questionId") int questionId) {
        Question question = questionService.getQuestionById(questionId);
        model.addAttribute("question", question);
        return "detail";
    }
}
