package com.wcl.zixunproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wcl.zixunproject.pojo.Comment;

@Mapper
public interface CommentDao {
    
    @Insert(value = { "insert into comment(content, user_id, entity_type, entity_id, created_date, status)"
            + " values(#{content}, #{userId}, #{entityType}, #{entityId}, #{createdDate}, #{status})" })
    int addComment(Comment comment);
    
    @Select(value = { "select id, content, user_id, entity_type, entity_id, created_date, status from comment"
            + " where entity_type = #{entityType} and entity_id = #{entityId} and status = 0" })
    List<Comment> getCommentByEntity(@Param("entityType") int entityType, @Param("entityId") int entityId);
    
    @Update(value = { "update comment set status = #{status} where id = #{id}" })
    int updateCommentStatus(@Param("id") int id, @Param("status") int status);
    
    @Select(value = { "select count(id) from comment where entity_type = #{entityType} and entity_id = #{entityId} and status = 0" })
    int getCommentNumByEntity(@Param("entityType") int entityType, @Param("entityId") int entityId);
}
