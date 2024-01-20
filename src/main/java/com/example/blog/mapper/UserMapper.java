package com.example.blog.mapper;

import com.example.blog.entity.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {

    @Select("select * from m_user where id = #{id}")
    public User selectById(Long id);
    @Delete("delete from m_user where id = #{id}")
    public void deleteById(Long id);
    @Insert("insert into m_user (username,password,avatar,email,status,created,last_login) values " +
            "(#{username},#{password},#{avatar},#{email},#{status},#{created},#{lastLogin})")
    public void insert(User user);
    @Update("update m_user set username=#{username},password=#{password},avatar=#{avatar}," +
            "email=#{email},status=#{status},created=#{created},last_login=#{lastLogin} where id=#{id}")
    public void update(User user);
    @Select("select * from m_user where username = #{username}")
    public User selectByUsername(String username);

    @Insert("insert into m_user (username,password,avatar,email,status,created,last_login) values " +
            "(#{username},#{password},1,1,1,NOW(),NOW())")
    void add(String username, String password);

}
