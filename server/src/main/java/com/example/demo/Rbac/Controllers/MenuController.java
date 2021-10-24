package com.example.demo.Rbac.Controllers;

import com.example.demo.Cofigs.Rtn;
import com.example.demo.Rbac.Entitys.Menu;
import com.example.demo.Rbac.Mappers.MenuMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation("菜单管理")
@RestController
@RequestMapping("api/menu")
public class MenuController {
    @Autowired
    private MenuMapper menuMapper;

    @PostMapping("createMenu")
    public Rtn<Boolean> addMenu(@RequestBody Menu menu){
        //  Menu menu1 = AutoMapper.INSTANCE.addDtoToMenu(menu1);

        return Rtn.Success(true);
    }
    @DeleteMapping("delete/{id}")
    public Rtn<Boolean> deleteRoleById(@PathVariable int id ){
        menuMapper.deleteById(id);
        return Rtn.Success(true);
    }
    @PostMapping("update")
    public Rtn<Boolean> updateMenu(@RequestBody Menu menu){

        return Rtn.Success(true);
    }
    @GetMapping("query")
    public Rtn<List<Menu>> queryAll(Menu menu){
        //  List<Menu> menus = menuMapper.queryAll(menu);
        List<Menu> menus = menuMapper.queryAll(menu);
        return Rtn.Success(menus);


    }

}
