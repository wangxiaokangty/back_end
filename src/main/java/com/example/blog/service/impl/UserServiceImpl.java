package com.example.blog.service.impl;

import com.example.blog.common.dto.LoginDto;
import com.example.blog.entity.User;
import com.example.blog.mapper.UserMapper;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }
    public User getOne(LoginDto loginDto) {
        String userName=loginDto.getUsername();
        return userMapper.selectByUsername(userName);
    }

    @Override
    public void register(String username, String password) {

        //添加
        userMapper.add(username,password);
    }
}
