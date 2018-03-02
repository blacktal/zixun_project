package com.wcl.zixunproject.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcl.zixunproject.dao.QuestionDao;
import com.wcl.zixunproject.pojo.Question;

@Service
public class QuestionService {
    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);
    
    @Autowired
    QuestionDao questionDao;
    
    public List<Question> getLatestQuestions(int userId, int offset, int limit) {
        return questionDao.getLatestQuestions(userId, offset, limit);
    }
    
    public Question getQuestionById(int questionId) {
        return questionDao.getQuestionById(questionId);
    }
    
}
