package com.example.blog.service;

import com.example.blog.common.dto.LoginDto;
import com.example.blog.entity.User;

public interface UserService {

    public User getById(Long id);
    public User getOne(LoginDto loginDto);

    void register(String username, String password);
}
