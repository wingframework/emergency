package com.example.demo.commons.Controllers;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import com.example.demo.Cofigs.Rtn;
import com.example.demo.commons.sevices.SmsService;
import com.example.demo.passport.entitys.Sms;
import com.example.demo.passport.entitys.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@ApiOperation()
@RestController
@RequestMapping("test")
public class SmsController { //sms是短信息服务 在DVB系统中，用户管理系统(SMS，Subscriber Management System)是十分重要的一环
    @Autowired
    SmsService smsService;

    @GetMapping("sms")
    public Rtn<Boolean> test(String phone,String code) throws  Exception{

    smsService.sendSms(phone,code);
    return  Rtn.Success(true);

    }


}
