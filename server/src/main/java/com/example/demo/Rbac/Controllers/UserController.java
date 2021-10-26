package com.example.demo.Rbac.Controllers;

import com.example.demo.AutoMapper;
import com.example.demo.Cofigs.Rtn;
import com.example.demo.Rbac.Dto.RoleUpdateDto;
import com.example.demo.Rbac.Dto.UserAddInputDto;
import com.example.demo.Rbac.Dto.UserUpdateDto;
import com.example.demo.Rbac.Entitys.Menu;
import com.example.demo.Rbac.Entitys.Role;
import com.example.demo.Rbac.Entitys.User;
import com.example.demo.Rbac.services.jpa.RoleJpa;
import com.example.demo.Rbac.services.jpa.UserJpa;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("detail/{id}")
    public Rtn<User> detailById(int userId){
        return Rtn.Success(userJpa.findById(userId).get());
    }

    @PostMapping("userUpdate")
    public Rtn<Boolean> userUpdate(@RequestBody UserUpdateDto userUpdateDto) {
        User user = AutoMapper.INSTANCE.updateDtoToUser(userUpdateDto);
        List<Role> roles = roleJpa.findByIdIn(userUpdateDto.getRoleIdList());
        user.setRoleList(roles);
        userJpa.saveAndFlush(user);
        return Rtn.Success(true);
    }











}
