package com.example.demo.Rbac.Controllers;

import com.example.demo.AutoMapper;
import com.example.demo.Cofigs.Rtn;
import com.example.demo.Rbac.Dto.RoleAddInputDto;
import com.example.demo.Rbac.Entitys.Menu;
import com.example.demo.Rbac.Entitys.Role;
//import com.example.demo.Rbac.Mappers.RoleMapper;
import com.example.demo.Rbac.services.jpa.MenuJpa;
import com.example.demo.Rbac.services.jpa.RoleJpa;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@ApiOperation("角色管理")
@RestController
@RequestMapping("api/role")
public class RoleController {
  @Autowired
  private   RoleJpa roleJpa;
    @Autowired
  private MenuJpa menuJpa;


    @PostMapping("roleAdd")
    public Rtn<Boolean> roleAdd(RoleAddInputDto roleAddInputDto) {
        Role role = AutoMapper.INSTANCE.roleAddDtoToRole(roleAddInputDto);
       List<Menu>  menus=   menuJpa.findByIdIn(roleAddInputDto.getMenuIdList());
       role.setMenuList(menus);
        roleJpa.saveAndFlush(role);

    return  Rtn.Success(true);
    }

     @PostMapping("detail/{id}")
     public Rtn<Role> detailById(int roleId) {
         return Rtn.Success(roleJpa.findById(roleId).get());
     }
}
