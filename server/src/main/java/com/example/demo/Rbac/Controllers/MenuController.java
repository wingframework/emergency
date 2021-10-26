package com.example.demo.Rbac.Controllers;

import com.example.demo.Cofigs.Rtn;
import com.example.demo.Rbac.Entitys.Menu;
import com.example.demo.Rbac.services.jpa.MenuJpa;
import com.example.demo.Rbac.services.jpa.UserJpa;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping("")
    public Rtn<Boolean> deleteById(int id){
        menuJpa.deleteById(id);
        return Rtn.Success(true);
    }


















}
