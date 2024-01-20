package com.example.blog.service.impl;

import com.example.blog.common.Page.IPage;
import com.example.blog.entity.Blog;
import com.example.blog.mapper.BlogMapper;
import com.example.blog.service.BlogService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getById(Long id) {
        return blogMapper.selectById(id);
    }

    public IPage<Blog> page(Page page){
        List<Blog> blogs = blogMapper.selectByCondition(page.getPageSize(),page.getStartRow());
        Long total=blogMapper.selectCount();
        int size=page.getPageSize();
        int current=page.getPageNum();
        int pages=(int)Math.ceil((double)total/size);
        return new IPage(blogs,total,size,current,pages);
    }
    public void saveOrUpdate(Blog blog){
        if(blog.getId()==null){
            blogMapper.insert(blog);
        }else{
            blogMapper.update(blog);
        }
    }
}

