package com.example.demo.Rbac.Controllers;

import com.example.demo.AutoMapper;
import com.example.demo.Cofigs.Rtn;
import com.example.demo.Rbac.Dto.UserrAddInputDto;
import com.example.demo.Rbac.Dto.UserrQueryInput;
import com.example.demo.Rbac.Dto.UserrQueryOutput;
import com.example.demo.Rbac.Dto.UserrUpdateDto;
import com.example.demo.Rbac.Entitys.Userr;
import com.example.demo.Rbac.Entitys.UserrRole;
import com.example.demo.Rbac.Mappers.UserrMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@ApiOperation("用户管理")
@RestController
@RequestMapping("api/userr")
public class UserrController {
    @Autowired
    private UserrMapper userrMapper;

    @PostMapping("UserrAdd")
    public Rtn<Boolean> UserrAdd(@RequestBody UserrAddInputDto userrAddInputDto){
        Userr userr = AutoMapper.INSTANCE.addDtoToUserr(userrAddInputDto);
        userrMapper.UserrAdd(userr);

        Userr userrByUsername = userrMapper.findUserrByUsername(userrAddInputDto.getUsername());
        List<UserrRole> userrRoles = new LinkedList<>();
        for(int i=0;i<userrAddInputDto.getRoleIdList().size();i++){
            UserrRole userrRole = new UserrRole();
            userrRole.setUserId(userrByUsername.getId());
            userrRole.setRoleId(userrAddInputDto.getRoleIdList().get(i));
            userrRoles.add(userrRole);
        }
        userrMapper.addUserrRoles(userrRoles);
        return Rtn.Success(true);

    }

    @PostMapping("detail/{userrId}")
    public Rtn<List<Integer>>  detailByUserrId(int userrId){
        List<Integer> roleIdByUserrID = userrMapper.findRoleIdByUserrID(userrId);
        return Rtn.Success(roleIdByUserrID);
    }

    @PostMapping("UserrUpdate")
    public Rtn<Boolean> userrUpdate(@RequestBody UserrUpdateDto userrUpdateDto ){
          userrMapper.deleteByUserrId(userrUpdateDto.getId());
        Userr userrById = userrMapper.findUserrById(userrUpdateDto.getId());
         List<UserrRole> userrRoles = new LinkedList<>();
         for (int i=0;i<userrUpdateDto.getRoleIdList().size();i++){
             UserrRole userrRole = new UserrRole();
             userrRole.setUserId(userrById.getId());
             userrRole.setRoleId(userrUpdateDto.getRoleIdList().get(i));
             userrRoles.add(userrRole);
         }
         userrMapper.addUserrRoles(userrRoles);
         userrMapper.resetPasswordById(userrUpdateDto.getId());
         return Rtn.Success(true);
    }

    @DeleteMapping("delete/{id}")
    public Rtn<Boolean> userrDelete(@PathVariable int id){
        userrMapper.deleteById(id);
        return Rtn.Success(true);
    }
    @GetMapping("list")
    public Rtn<UserrQueryOutput> getRole(UserrQueryInput userrQueryInput){
        List<Userr> userrs = userrMapper.pageQueryByKeyword(userrQueryInput);
        int total = userrMapper.pageQueryByKeywordCount(userrQueryInput);
        UserrQueryOutput result = new UserrQueryOutput();
        result.setTotal(total);
        result.setData(userrs);

        return Rtn.Success(result);

    }




}
