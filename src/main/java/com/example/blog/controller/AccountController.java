package com.example.blog.controller;

import cn.hutool.core.map.MapUtil;
import com.example.blog.common.dto.LoginDto;
import com.example.blog.common.dto.RegisterDto;
import com.example.blog.entity.Result;
import com.example.blog.entity.User;
import com.example.blog.service.UserService;
import com.example.blog.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class AccountController {
    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {


        User user = userService.getOne(loginDto);
        if (user == null) {
            return Result.error("用户不存在");
        }

        if (!user.getPassword().equals(loginDto.getPassword())) {
            return Result.error("密码不正确");
        }

        String jwt = jwtUtil.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");


        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    @RequestMapping ("/register")
    public Result register(@Validated @RequestBody RegisterDto registerDto) {

        String username = registerDto.getUsername();
        String password = registerDto.getPassword();
        System.out.println(username);
        userService.register(username, password);
        return Result.success();

    }


    //@RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        //SecurityUtils.getSubject().logout();//这个类是属于shiro的
        return Result.success(null);
    }
}
