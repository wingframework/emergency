package com.example.demo.Rbac.Controllers;

import com.example.demo.AutoMapper;
import com.example.demo.Cofigs.Rtn;
import com.example.demo.Rbac.Dto.RoleAddInputDto;
import com.example.demo.Rbac.Entitys.Role;
import com.example.demo.Rbac.Entitys.RoleMenu;
import com.example.demo.Rbac.Mappers.RoleMapper;
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
    private RoleMapper roleMapper;

    @PostMapping("roleAdd")
    public Rtn<Boolean> roleAdd(RoleAddInputDto roleAddInputDto) {
        Role role = AutoMapper.INSTANCE.roleAddDtoToRole(roleAddInputDto);
        roleMapper.roleAdd(role);
        Role roleByCode = roleMapper.findRoleByCode(roleAddInputDto.getCode());
        List<RoleMenu> roleMenus = new LinkedList<>();
        for (int i = 0; i < roleAddInputDto.getMenuIdList().size(); i++) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleByCode.getId());
            roleMenu.setMenuId(roleAddInputDto.getMenuIdList().get(i));
            roleMenus.add(roleMenu);
        }
        roleMapper.addRoleMenus(roleMenus);
        return Rtn.Success(true);
    }

    // @PostMapping("detail/{id}")
    // public Rtn<List<Integer>> detailById(int roleId) {

    // }

}
