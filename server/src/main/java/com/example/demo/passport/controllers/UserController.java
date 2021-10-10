package com.example.demo.passport.controllers;

import com.example.demo.AutoMapper;
import com.example.demo.Cofigs.Rtn;
import com.example.demo.commons.sevices.SmsService;
import com.example.demo.passport.Dto.*;
import com.example.demo.commons.mappers.SmsMapper;
import com.example.demo.passport.entitys.Sms;
import com.example.demo.passport.entitys.User;
import com.example.demo.passport.Mappers.UserMapper;
import com.example.demo.Cofigs.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;


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
    @Autowired
    private SmsService smsService;
    @Autowired
    private SmsMapper smsMapper;

    @PostMapping("register")
    public Rtn<RegisterUserOutput> RegisterUser(@Validated @RequestBody RegisterInputDto registerInputDto){


       User newUser = AutoMapper.INSTANCE.RegisterDtoToUser(registerInputDto);

         String num = registerInputDto.getPhoneNumber();
        // Sms sms= this.smsService.getLastSmsExpire(newUser.getPhonenumber(),5);
        Sms sms= this.smsService.getLastSmsExpire(num,5);
        if(sms==null){
            return   Rtn.Error("请先发短信");

        }   else {
            if(!sms.getCode().equals(registerInputDto.getCode())) {

                return Rtn.Error("验证码错误");
            }
        }
                              //Re:renewedly,重新地，再度
        if(!registerInputDto.getRePassword().equals(registerInputDto.getPassword())){
            return   Rtn.Error("两次密码不一致");
        }
       int  UserExsitCount = userMapper.CountUserExsit(newUser);
       if( UserExsitCount>0 ){
           return Rtn.Error("用户名已存在或手机号已存在");
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
         return Rtn.Error("您输入的用户名不存在，请先去注册");
        }
             String a = userLoginDto.getUsername();
          User dbUser=  userMapper.FindUserByUsername( a);

                                 //拿到近几分钟的最后一条短信
//        Sms sms= this.smsService.getLastSmsExpire(dbUser.getPhoneNumber(),5);
//        if(sms==null){
//            return   Rtn.Error("请先发短信");
//
//        }   else {
//            if(!sms.getCode().equals(userLoginDto.getCode())) {
//
//                return Rtn.Error("验证码错误");
//            }
//        }
          if(dbUser.getPassword().equals(userLoginDto.getPassword())){
              String token = tokenService.getToken(dbUser);
              LoginUserOutput data2 = new LoginUserOutput(token);
              return Rtn.Success(data2);
          } else{
              return Rtn.Error("用户名或密码错误");
          }
    }

    @PostMapping("ForgetPassword")
    public Rtn<Boolean> UserForgetPassword(@RequestBody UserForgetPasswordDto userForgetPasswordDto){
        User user2 = AutoMapper.INSTANCE.ForgetDtoToUser(userForgetPasswordDto);
        String a1 = userForgetPasswordDto.getRepassword();
        String a = userForgetPasswordDto.getPhonenumber();
        String num2 = userForgetPasswordDto.getPhonenumber();
        Sms sms= this.smsService.getLastSmsExpire(num2,5);
        if(sms==null){
            return   Rtn.Error("请先发短信");

        }   else {
            if(!sms.getCode().equals(userForgetPasswordDto.getCode())) {

                return Rtn.Error("验证码错误");
            }
        }

        if(!a1.equals(a) ){
            return Rtn.Error("两次输入的密码不一致,请重新输入");
        } else {
              String num =   userForgetPasswordDto.getPhonenumber();
            User user= userMapper.FindUserByPhoneNumber( num);
            if(user==null){
                return Rtn.Error("该用户不存在,请重新输入电话号码");
            }else{
                String b = userForgetPasswordDto.getPassword();
                userMapper.UpdatePasswordByPhoneNumber(a,b);
            }
            return Rtn.Success(true);

        }
    }

    @GetMapping("GetUserInfo")
    public Rtn<User> GetUserInfo( ){

        User user = tokenService.getUser();
        return Rtn.Success(user);
    }

    @GetMapping("sentSms")
    public Rtn<Boolean> SentSms(@Validated SentSmsInput sentSmsInput ) throws  Exception{
      Sms lastSms=  this.smsService.getLastSmsExpire(sentSmsInput.getPhonenumber(),5);
//      if(lastSms!=null){
//         return Rtn.Error("稍后尝试");
//      }
       String code=   UUID.randomUUID().toString().substring(0,4);
       smsService.sendSms( sentSmsInput.getPhonenumber(),code);
       Sms sms= new Sms();
       sms.setPhonenumber(sentSmsInput.getPhonenumber());
       sms.setCode(code);
       sms.setSentAt(new Date());
       smsMapper.Create(sms);


        return  Rtn.Success(true);
    }

//    public Rtn<List<Sms>> querySentSmsExpire( QuerySentSmsExpire querySentSmsExpire){
//
//
//             return Rtn.Success(list);
//
//    }

}
