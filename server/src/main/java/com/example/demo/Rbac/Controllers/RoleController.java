package com.example.demo.Rbac.Controllers;

import com.example.demo.AutoMapper;
import com.example.demo.Cofigs.Rtn;
import com.example.demo.Rbac.Dto.RoleAddInputDto;
import com.example.demo.Rbac.Dto.RoleUpdateDto;
import com.example.demo.Rbac.Entitys.Menu;
import com.example.demo.Rbac.Entitys.Role;
import com.example.demo.Rbac.services.jpa.MenuJpa;
import com.example.demo.Rbac.services.jpa.RoleJpa;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("detail/{id}")
    public Rtn<Role> detailById(int roleId) {
        return Rtn.Success(roleJpa.findById(roleId).get());
    }

    @PostMapping("roleUpdate")
    public Rtn<Boolean> roleUpdate(@RequestBody RoleUpdateDto roleUpdateDto) {
        Role role = AutoMapper.INSTANCE.updateDtoToRole(roleUpdateDto);
        List<Menu> menus = menuJpa.findByIdIn(roleUpdateDto.getMenuIdList());
        role.setMenuList(menus);
        roleJpa.saveAndFlush(role);
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
