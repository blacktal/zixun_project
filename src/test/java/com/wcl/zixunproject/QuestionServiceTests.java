package com.wcl.zixunproject;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wcl.zixunproject.pojo.Question;
import com.wcl.zixunproject.service.QuestionService;
import com.wcl.zixunproject.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceTests {
    
    @Autowired
    UserService userService;
    
    @Autowired
    QuestionService questionService;
    
	@Test
	public void getLatestQuestionsTest() {
        List<Question> questionList = questionService.getLatestQuestions(0, 0, 10);
        assertNotNull("为空", questionList);
        System.out.println(questionList.size());
        for (Question question : questionList) {
            System.out.println(question.getTitle());
        }
	}

}
