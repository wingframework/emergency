package com.example.saas.rbac.controllers;

import com.example.saas.AutoMapper;
import com.example.saas.configs.Rtn;
import com.example.saas.rbac.dto.UserAddInputDto;
import com.example.saas.rbac.dto.UserUpdateDto;
import com.example.saas.rbac.entitys.Role;
import com.example.saas.rbac.entitys.User;
import com.example.saas.rbac.services.jpa.RoleJpa;
import com.example.saas.rbac.services.jpa.UserJpa;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation("用户管理")
@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserJpa userJpa;
    @Autowired
    private RoleJpa roleJpa;

    @PostMapping("userAdd")
    public Rtn<Boolean> userAdd(UserAddInputDto userAddInputDto){
        User user = AutoMapper.INSTANCE.addDtoToUser(userAddInputDto);
        List<Role> roles = roleJpa.findByIdIn(userAddInputDto.getRoleIdList());
        user.setRoleList(roles);
        userJpa.saveAndFlush(user);
        return Rtn.Success(true);
    }
    @GetMapping("detail/{id}")
    public Rtn<User> detailById(int id){

        return Rtn.Success(userJpa.findById(id).get());
    }

    @PostMapping("userUpdate")
    public Rtn<Boolean> userUpdate(@RequestBody UserUpdateDto userUpdateDto) {
        User user = AutoMapper.INSTANCE.updateDtoToUser(userUpdateDto);
        List<Role> roles = roleJpa.findByIdIn(userUpdateDto.getRoleIdList());
        user.setRoleList(roles);
        userJpa.saveAndFlush(user);
        return Rtn.Success(true);
    }

    @DeleteMapping("deleteById")
    public Rtn<Boolean> deleteById(int id){
        userJpa.deleteById(id);
        return Rtn.Success(true);
    }
    @GetMapping("userQuery")
    public Rtn<Boolean> userQuery(){
        userJpa.findAll();
        return Rtn.Success(true);
    }













}
