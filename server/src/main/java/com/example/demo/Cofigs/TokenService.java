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

    public User getUser() {

        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token

        int userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        return userService.findUserById(userId);

    }

    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(user.getId() + "").sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
    // 1、Token的引入：Token是在客户端频繁向服务端请求数据，服务端频繁的去数据库查询用户名和密码并进行对比，
    // 判断用户名和密码正确与否，并作出相应提示，在这样的背景下，Token便应运而生。
    //
    // 2、Token的定义：Token是服务端生成的一串字符串，以作客户端进行请求的一个令牌，当第一次登录后，
    // 服务器生成一个Token便将此Token返回给客户端，以后客户端只需带上这个Token前来请求数据即可，无需再次带上用户名和密码。
    //
    // 3、使用Token的目的：Token的目的是为了减轻服务器的压力，减少频繁的查询数据库，使服务器更加健壮。
    //
    // 作者：9264oo
    // 链接：https://www.jianshu.com/p/24825a2683e6
    // 来源：简书
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

}