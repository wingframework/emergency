package com.example.demo.Cofigs;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.example.demo.passport.entitys.User;
import com.example.demo.passport.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class TokenService {
    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest httpServletRequest;
    public User getUser(){

        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token

        int  userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        return     userService.findUserById(userId);

    }
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getId()+"")
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}