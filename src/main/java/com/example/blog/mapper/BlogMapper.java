package com.example.blog.mapper;

import com.example.blog.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    @Select("select * from m_blog where id = #{id}")
    public Blog selectById(Long id);
    @Delete("delete from m_blog where id = #{id}")
    public void deleteById(Long id);
    @Insert("insert into m_blog (user_id,title,description,content,created,status) values " +
            "(#{user_Id},#{title},#{description},#{content},#{created},#{status})")
    public void insert(Blog blog);
    @Update("update m_blog set user_id=#{user_Id},title=#{title},description=#{description}," +
            "content=#{content},created=#{created},status=#{status} where id=#{id}")
    public void update(Blog blog);

    @Select("SELECT * FROM m_blog ORDER BY created DESC limit #{limit} offset #{offset}")
    public List<Blog> selectByCondition(int limit, Long offset);
    @Select("select count(*) from m_blog")
    public Long selectCount();
}
