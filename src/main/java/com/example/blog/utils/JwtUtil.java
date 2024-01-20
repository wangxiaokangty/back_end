package com.example.blog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static String signKey = "jokerjokerjokerjokerjok" +
            "erjokerjokerjokerjokerjokerjokerjokerjokerjokerjokerjokerjokerjokerjokerjokerjoke" +
            "rjokerjokerjokerjokerjokerjokerjokerjokerjokerjokerjokerjokerjoker" +
            "jokerjokerjokerjokerjokerjoker";
    private static Long expire = 60 * 60 * 1000 * 1L;


    public static String generateToken(long number){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",number);

        return generateJwt(claims);
    }

    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)//自定义信息（有效载荷）
                .signWith(SignatureAlgorithm.HS256, signKey)//签名算法（头部）
                .setExpiration(new Date(System.currentTimeMillis() + expire))//过期时间
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)//指定签名密钥
                .parseClaimsJws(jwt)//指定令牌Token
                .getBody();
        return claims;
    }

}
