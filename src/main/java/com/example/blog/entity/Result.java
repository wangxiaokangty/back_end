package com.example.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应结果封装类
 * 将返回给前端的 json 封装到一个统一的实体类中
 * 有三个静态方法来快速的创建返回 json
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code ;//201 成功 , 401 失败
    private String msg; //提示信息
    private Object data; //数据 date

    public static Result success(Object data){
        return new Result(201, "操作成功", data);
    }
    public static Result success(){
        return new Result(201, "操作成功", null);
    }
    public static Result error(String msg){
        return new Result(401, msg, null);
    }

}

