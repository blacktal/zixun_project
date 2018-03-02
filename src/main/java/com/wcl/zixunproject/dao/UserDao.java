package com.wcl.zixunproject.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wcl.zixunproject.pojo.User;

@Mapper
public interface UserDao {
    
    @Insert(value = { "insert into user(name,password,head_url,salt) values(#{name},#{password},#{headUrl},#{salt})" })
    int addUser(User user);
    
    @Select(value = { "select id,name,password,head_url,salt from user where id = #{id}" })
    User getUserById(int id);
    
    @Select(value = { "select id,name,password,head_url,salt from user where name = #{name}" })
    User getUserByName(String name);
    
    @Update(value = { "update login_ticket set status = #{status} where ticket = #{ticket}" })
    void updateTicketStatus(@Param("ticket") String ticket, @Param("status") int status);
}
