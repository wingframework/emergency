package com.example.saas.common.controllers;

import com.example.saas.configs.Rtn;
import com.example.saas.common.sevices.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
