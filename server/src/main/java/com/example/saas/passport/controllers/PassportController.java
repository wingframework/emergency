package com.example.saas.passport.controllers;

import com.example.saas.AutoMapper;
import com.example.saas.configs.Rtn;
import com.example.saas.configs.TokenService;
import com.example.saas.rbac.entitys.User;
import com.example.saas.common.mappers.SmsMapper;
import com.example.saas.common.sevices.SmsService;
import com.example.saas.passport.dto.*;
import com.example.saas.passport.mappers.UserMapper;
import com.example.saas.passport.entitys.Sms;
import com.example.saas.rbac.services.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/passport/passport")
public class PassportController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SmsService smsService;
    @Autowired
    private SmsMapper smsMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserJpa userJpa;

    @PostMapping("register")
    public Rtn<RegisterUserOutput> registerUser(@Validated @RequestBody RegisterInputDto registerInputDto) {
        User user = AutoMapper.INSTANCE.RegisterDtoToUser(registerInputDto);
        int userExsitCount = userMapper.countUserExsit(user);
        if (userExsitCount > 0) {
            return Rtn.Error("您输入的手机号或者用户名已注册");
        }
        Sms sms = smsService.getLastSmsNotExpire(registerInputDto.getPhoneNumber(), 3, registerInputDto.getType());
        if (sms == null) {
            return Rtn.Error("请先发送短信");
        } else {
            if (!registerInputDto.getCode().equals(sms.getCode())) {
                return Rtn.Error("您输入的验证码错误");
            }
        }

        if (!registerInputDto.getRePassword().equals(registerInputDto.getPassword())) {
            return Rtn.Error("两次输入的密码不一致，请重新输入");
        }
        userMapper.create(user);
        String token = tokenService.getToken(user);
        RegisterUserOutput data = new RegisterUserOutput(token);
        return Rtn.Success(data);
    }

    @PostMapping("login")
    public Rtn<LoginUserOutput> loginUser(@Validated @RequestBody UserLoginDto userLoginDto) {
        User user = AutoMapper.INSTANCE.LoginDtoToUser(userLoginDto);
        User exsitUser = userJpa.findByUsername(user.getUsername());
        if (exsitUser == null) {
            return Rtn.Error("该用户名未注册，请先去注册");
        }


        if (!userLoginDto.getPassword().equals(exsitUser.getPassword())) {
            return Rtn.Error("您输入的密码不匹配");
        }
        String token = tokenService.getToken(exsitUser);
        LoginUserOutput data2 = new LoginUserOutput(token);
        return Rtn.Success(data2);

    }

    @PostMapping("forgetPassword")
    public Rtn<Boolean> forgetPasswordUser(@Validated @RequestBody ForgetPasswordUserDto forgetPasswordUserDto) {
        User user = AutoMapper.INSTANCE.ForgetDtoToUser(forgetPasswordUserDto);
        int userExsitCount = userMapper.countUserExsit(user);
        if (userExsitCount == 0) {
            return Rtn.Error("该用户未注册");
        }
        Sms sms = smsService.getLastSmsNotExpire(forgetPasswordUserDto.getPhoneNumber(), 3,
                forgetPasswordUserDto.getType());
        if (sms == null) {
            return Rtn.Error("请先发送短信");
        } else {
            if (!forgetPasswordUserDto.getCode().equals(sms.getCode())) {
                return Rtn.Error("您输入的验证码错误");
            }
        }

        if (!forgetPasswordUserDto.getRePassword().equals(forgetPasswordUserDto.getPassword())) {
            return Rtn.Error("两次输入的密码不一致");
        }
        userMapper.updatePasswordByPhoneNumber(forgetPasswordUserDto.getPhoneNumber(),
                forgetPasswordUserDto.getPassword());
        return Rtn.Success(true);

    }

    @GetMapping("sentSms")
    public Rtn<Boolean> sentSms(@Validated SentSmsInput sentSmsInput) throws Exception {
        Sms lastSms = smsService.getLastSmsNotExpire(sentSmsInput.getPhonenumber(), 3, sentSmsInput.getType());
        // uuid是产生随机数
        String code = UUID.randomUUID().toString().substring(0, 4);
        smsService.sendSms(sentSmsInput.getPhonenumber(), code);
        Sms sms = new Sms();
        sms.setPhonenumber(sentSmsInput.getPhonenumber());
        sms.setType(sentSmsInput.getType());
        // sms.setCode(code);
        // sms.setSentAt(new Date());
        smsMapper.Create(sms);
        return Rtn.Success(true);

    }

    @DeleteMapping("delete/{id}")
    public Rtn<Boolean> deleteById(int id) {
        userMapper.deleteById(id);
        return Rtn.Success(true);
    }

    @GetMapping("GetUserInfo")
    public Rtn<User> GetUserInfo() {

        User user = tokenService.getUser();
        return Rtn.Success(user);
    }

}
