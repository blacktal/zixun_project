package com.wcl.zixunproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wcl.zixunproject.pojo.Message;

@Mapper
public interface MessageDao {
    
    @Insert(value = { "insert into message(content, from_id, to_id, created_date, has_read, conversation_id) values"
            + "(#{content}, #{fromId}, #{toId}, #{createdDate}, #{hasRead}, #{conversationId})" })
    int addMessage(Message message);
    
    @Select({"select content, from_id, to_id, created_date, has_read, conversation_id ,count(id) as id from ( select * from message where from_id=#{userId} or to_id=#{userId} order by id desc) tt group by conversation_id  order by created_date desc limit #{offset}, #{limit}"})
    List<Message> getMessageListByUserId(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit);
    
}
