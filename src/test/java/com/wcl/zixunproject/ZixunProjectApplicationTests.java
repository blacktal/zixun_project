package com.wcl.zixunproject;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.wcl.zixunproject.dao.QuestionDao;
import com.wcl.zixunproject.dao.UserDao;
import com.wcl.zixunproject.pojo.Question;
import com.wcl.zixunproject.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = ZixunProjectApplication.class)
@Sql(value="/init_schema.sql")
public class ZixunProjectApplicationTests {
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    QuestionDao questionDao;

	@Test
	public void contextLoads() {
	    Random random = new Random();
	    for(int i = 1; i < 11; i ++) {
	        
	        User user = new User();
	        user.setName("User" + i);
	        user.setPassword("123");
	        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
	        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
	        userDao.addUser(user);
	        
	        Question question = new Question();
	        question.setTitle("众所周知，这是第" + i + "个问题？");
	        question.setContent("你是十万个为什么吗");
	        question.setUserId(i);
	        Date date = new Date();
	        date.setTime(date.getTime() + 3600 * 1000 * 5 * i);
	        question.setCreatedDate(date);
	        question.setCommentNum(i + 10);
	        questionDao.addQuestion(question);
	        
	    }
	}

}
