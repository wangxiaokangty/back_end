package com.example.blog.interceptor;

import com.example.blog.entity.Result;
import com.example.blog.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override //目标资源方法运行前运行，放行返回 true，否则 false
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求url
        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行
        //3.获取请求头中的令牌（token）
        String token = request.getHeader("token");
        ObjectMapper objectMapper = new ObjectMapper();


        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(token)){

            //创建响应结果对象
            Result responseResult = Result.error("NOT_LOGIN");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = objectMapper.writeValueAsString(responseResult);
            //设置响应头（告知浏览器：响应的数据类型为json、响应的数据编码表为utf-8）
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);

            return false;//不放行
        }

        try {
            JwtUtil.parseJWT(token);
        }catch (Exception e){

            //创建响应结果对象
            Result responseResult = Result.error("NOT_LOGIN");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = objectMapper.writeValueAsString(responseResult);
            //设置响应头
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);

            return false;
        }

        //6.放行
        return true;


    }

    @Override //运行后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override //渲染完毕后执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
