package com.example.demo.Rbac.Controllers;

import com.example.demo.Cofigs.Rtn;
import com.example.demo.Rbac.Entitys.Menu;
import com.example.demo.Rbac.services.jpa.MenuJpa;
import com.example.demo.Rbac.services.jpa.UserJpa;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
        menuJpa.save(new Menu());
        return Rtn.Success(true);
    }

    @PostMapping()

}
