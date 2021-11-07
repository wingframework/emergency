package com.example.saas.central.enterpase.controllers;

import com.example.saas.central.enterpase.dtos.AdminLoginDto;
import com.example.saas.configs.Rtn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Controller("/api/admin")
public class AdminController {

    @PostMapping("login")
    public Rtn<String> Login(@RequestBody AdminLoginDto input){
        throw  new NotImplementedException();
    }
}
