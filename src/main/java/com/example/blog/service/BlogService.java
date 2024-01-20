package com.example.blog.service;

import com.example.blog.common.Page.IPage;
import com.example.blog.entity.Blog;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public interface BlogService {
    public Blog getById(Long id);
    public IPage<Blog> page(Page page);
    public void saveOrUpdate(Blog blog);
}
