package com.example.saas.rbac.controllers;

import com.example.saas.AutoMapper;
import com.example.saas.configs.Rtn;
import com.example.saas.rbac.dto.RoleAddInputDto;
import com.example.saas.rbac.dto.RoleUpdateDto;
import com.example.saas.rbac.entitys.Menu;
import com.example.saas.rbac.entitys.Role;
import com.example.saas.rbac.services.jpa.MenuJpa;
import com.example.saas.rbac.services.jpa.RoleJpa;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation("角色管理")
@RestController
@RequestMapping("api/role")
public class RoleController {
    @Autowired
    private RoleJpa roleJpa;
    @Autowired
    private MenuJpa menuJpa;


    @PostMapping("roleAdd")
    public Rtn<Boolean> roleAdd(RoleAddInputDto roleAddInputDto) {
        Role role = AutoMapper.INSTANCE.roleAddDtoToRole(roleAddInputDto);
        List<Menu> menus = menuJpa.findByIdIn(roleAddInputDto.getMenuIdList());
        role.setMenuList(menus);
        roleJpa.saveAndFlush(role);
        return Rtn.Success(true);
    }

    @GetMapping("detail/{id}")
    public Rtn<Role> detailById(int Id) {
        return Rtn.Success(roleJpa.findById(Id).get());
    }

    @PostMapping("roleUpdate")
    public Rtn<Boolean> roleUpdate(@RequestBody RoleUpdateDto roleUpdateDto) {
        Role role = AutoMapper.INSTANCE.updateDtoToRole(roleUpdateDto);
        List<Menu> menus = menuJpa.findByIdIn(roleUpdateDto.getMenuIdList());
        role.setMenuList(menus);
        roleJpa.saveAndFlush(role);
        return Rtn.Success(true);
    }

    @DeleteMapping("deleteById")
    public Rtn<Boolean> deleteById(int id){
        roleJpa.deleteById(id);
        return Rtn.Success(true);
    }

    @GetMapping("roleQuery")
    public Rtn<Boolean> roleQuery(){
        menuJpa.findAll();
        return Rtn.Success(true);
    }



//    @GetMapping("list")
//    public Rtn<RoleQueryOutput> getRole(RoleQueryInput roleQueryInput){
//        List<Role> data = roleMapper.pageQueryByKeyword(roleQueryInput);
//        int total = roleMapper.pageQueryByKeywordCount(roleQueryInput);
//        RoleQueryOutput result = new RoleQueryOutput();
//        result.setData(data);
//        result.setTotal(total);
//        return Rtn.Success(result);
//
//    }


}
