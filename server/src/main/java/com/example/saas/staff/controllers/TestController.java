//package com.example.demo.Staff.Controllers;
//
//import com.alibaba.fastjson.JSONObject;
//import com.example.demo.passport.entitys.User;
//import com.example.demo.Cofigs.TokenService;
//import com.example.demo.passport.services.UserService;
//import com.example.demo.Cofigs.annotations.UserLoginToken;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("test")
//public class TestController {
//    @Autowired
//    UserService userService;
//    @Autowired
//    TokenService tokenService;
//    //登录
//    @PostMapping("/login")
//    public Object login(@RequestBody User user){
//        JSONObject jsonObject=new JSONObject();
//
//        User userForBase=userService.findUserByUsername(user.getUsername());
//        if(userForBase==null){
//            jsonObject.put("message","登录失败,用户不存在");
//            return jsonObject;
//        }else {
//            if (!userForBase.getPassword().equals(user.getPassword())){
//                jsonObject.put("message","登录失败,密码错误");
//                return jsonObject;
//            }else {
//                String token = tokenService.getToken(userForBase);
//                jsonObject.put("token", token);
//                jsonObject.put("user", userForBase);
//                return jsonObject;
//            }
//        }
//    }
//    @UserLoginToken
//    @GetMapping("/getMessage")
//    public User getMessage(){
//        return tokenService.getUser();
//
////        return "你已通过验证";
//    }
//}