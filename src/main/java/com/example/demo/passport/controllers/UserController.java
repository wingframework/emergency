package com.example.demo.passport.controllers;

import com.example.demo.AutoMapper;
import com.example.demo.Cofigs.Rtn;
import com.example.demo.passport.Dto.*;
import com.example.demo.passport.entitys.User;
import com.example.demo.passport.Mappers.UserMapper;
import com.example.demo.Cofigs.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


//
@Api("用户api")
@RestController
@RequestMapping("api/user")

public class UserController {
    //实现引用类型的赋值
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenService tokenService;

    @PostMapping("register")
    public Rtn<RegisterUserOutput> RegisterUser(@Validated @RequestBody RegisterInputDto registerInputDto){


       User newUser = AutoMapper.INSTANCE.RegisterDtoToUser(registerInputDto);

        if(!registerInputDto.getRepassword().equals(registerInputDto.getPassword())){
            return   Rtn.Error("两次密码不一致");
        }
       int  UserExsitCount = userMapper.CountUserExsit(newUser);
       if( UserExsitCount>0 ){
           return Rtn.Error("用户名已存在");
       }
        userMapper.Create(newUser);
     String token=   tokenService.getToken(newUser);
       RegisterUserOutput data= new RegisterUserOutput(token);
         return  Rtn.Success(  data);
    }

    @PostMapping("login")
    @ApiOperation(value ="登录" )
    public Rtn<LoginUserOutput> UserLogin( @Validated @RequestBody  UserLoginDto userLoginDto){
        User user1 = AutoMapper.INSTANCE.LoginDtoToUser(userLoginDto);
        int UserExsitCount = userMapper.CountUserExsit(user1);
        if(UserExsitCount==0){
       // return "您输入的用户名不存在，请先去注册";
         return Rtn.Error("您输入的用户名不存在，请先去注册");
        }
        else{
          User dbUser=  userMapper.FindUserByUsername(userLoginDto.getUsername());
          if(dbUser.getPassword().equals(userLoginDto.getPassword())){
              String token = tokenService.getToken(dbUser);
              LoginUserOutput data2 = new LoginUserOutput(token);
              return Rtn.Success(data2);
          } else{
              return Rtn.Error("用户名或密码错误");
          }

        }

    }

    @PostMapping("ForgetPassword")
    public Rtn<Boolean> UserForgetPassword(@RequestBody UserForgetPasswordDto userForgetPasswordDto){
        User user2 = AutoMapper.INSTANCE.ForgetDtoToUser(userForgetPasswordDto);
        if(!userForgetPasswordDto.getRepassword().equals(userForgetPasswordDto.getPassword()) ){
//                   return "两次输入的密码不一致,请重新输入";
            return Rtn.Error("两次输入的密码不一致,请重新输入");
        } else {

            User user= userMapper.FindUserByPhonenumber( userForgetPasswordDto.getPhonenumber());
            if(user==null){
                 //   return "该用户不存在";
                return Rtn.Error("该用户不存在");
            }else{
                userMapper.UpdatePasswordByPhonenumber(userForgetPasswordDto.getPhonenumber(),userForgetPasswordDto.getPassword());
            }
           // return "密码重置成功";

            return Rtn.Success(true);

        }
    }

    @GetMapping("GetUserInfo")
    public Rtn<User> GetUserInfo( ){

        User user = tokenService.getUser();
        return Rtn.Success(user);
    }



}
