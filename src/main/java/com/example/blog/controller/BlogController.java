package com.example.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.blog.common.Page.IPage;
import com.example.blog.entity.Blog;
import com.example.blog.entity.Result;
import com.example.blog.service.BlogService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController

public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/blog/{id}")
    @CrossOrigin
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已被删除");

        return Result.success(blog);
    }

    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {


        Blog temp = null;

        temp = new Blog();
        temp.setUser_Id(1L);//这里理论上是登陆者的Id
        temp.setCreated(LocalDateTime.now());
        temp.setStatus(0);

        //id为空或者id在m_blog中存在都可以达到修改
        BeanUtil.copyProperties(blog, temp, "id", "user_Id", "created", "status");
        blogService.saveOrUpdate(temp);

        return Result.success(null);
    }

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage, 5);
        IPage<Blog> pageData = blogService.page(page);
        return Result.success(pageData);
    }

}

