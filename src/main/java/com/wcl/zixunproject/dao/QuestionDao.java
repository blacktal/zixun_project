package com.wcl.zixunproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wcl.zixunproject.pojo.Question;

@Mapper
public interface QuestionDao {
    
    @Insert(value = { "insert into question(title,content,created_date,comment_num,user_id) values(#{title},#{content},#{createdDate},#{commentNum},#{userId})" })
    int addQuestion(Question question);
    
    //@Select({" select * from question where user_id = #{userId} "})
    List<Question> getLatestQuestions(@Param("userId") int userId,
                               @Param("offset") int offset,
                               @Param("limit") int limit);
}
