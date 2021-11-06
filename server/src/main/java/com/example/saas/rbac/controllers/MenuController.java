package com.example.saas.rbac.controllers;

import com.example.saas.configs.Rtn;
import com.example.saas.rbac.entitys.Menu;
import com.example.saas.rbac.services.jpa.MenuJpa;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@ApiOperation("菜单管理")
@RestController
@RequestMapping("api/menu")
public class MenuController {
    @Autowired
    private MenuJpa menuJpa;

    @PostMapping("menuAdd")
    public Rtn<Boolean> menuAdd(Menu menu){
        menuJpa.save(menu);
        return Rtn.Success(true);
    }

    @PostMapping("menuUpdate")
    public Rtn<Boolean> menuUpdate(Menu menu){
        menuJpa.save(menu);
        return Rtn.Success(true);
    }

    @DeleteMapping("deleteById")
    public Rtn<Boolean> deleteById(int id){
        menuJpa.deleteById(id);
        return Rtn.Success(true);
    }
    @GetMapping("menuQuery")
    public Rtn<Boolean> menuQuery(){
        menuJpa.findAll();
        return Rtn.Success(true);
    }









}
