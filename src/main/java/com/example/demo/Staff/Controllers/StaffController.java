package com.example.demo.Staff.Controllers;

import com.example.demo.AutoMapper;
import com.example.demo.Cofigs.Rtn;
import com.example.demo.Staff.Dto.AddStaffDto;
import com.example.demo.Staff.Dto.StaffQueryInput;
import com.example.demo.Staff.Dto.StaffQueryOutput;
import com.example.demo.Staff.Dto.UpdateStaffDto;
import com.example.demo.Staff.entitys.Staff;
import com.example.demo.Staff.Mappers.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/staff")
public class StaffController {
    @Autowired
    private StaffMapper staffMapper;

    @DeleteMapping("delete/{id}")
    public boolean DeleteStaffById(@PathVariable int id){
        staffMapper.DeleteById(id);
        return true;
    }

    @GetMapping("disable/{id}")
    public boolean DisableStaffById(@PathVariable int id){
        staffMapper.DisableById(id);
        return true;
    }


    @GetMapping("list")
    public Rtn<StaffQueryOutput> GetStaff(StaffQueryInput staffQueryInput){
        List<Staff> data= staffMapper.pageQueryByKeyword(staffQueryInput);
        int total= staffMapper.pageQueryByKeywordCount(staffQueryInput);
      StaffQueryOutput result=  new StaffQueryOutput();
      result.setData(data);
      result.setTotal(total);
      return Rtn.Success(result);
    }

    @PostMapping("Create")
    public Rtn<Boolean> AddStaff(@RequestBody AddStaffDto addStaffDto){

        Staff sta1 = AutoMapper.INSTANCE.AddDtoToStaff(addStaffDto);

        int  StaffExsitCount1 = staffMapper.CountzbrExsit(sta1);


        if( StaffExsitCount1>0 ){
          //  return "你输入的用户名或者名称已被他人使用";
            return Rtn.Error("你输入的用户名或者名称已被他人使用");
        }
        staffMapper.Create(sta1);
        return Rtn.Success(true);
    }

    @PostMapping("Update")
    public Rtn<Boolean> UpdateZbr(@RequestBody UpdateStaffDto updateStaffDto){

        Staff sta2 = AutoMapper.INSTANCE.UpdateDtoToStaff(updateStaffDto);
        int  StaExsitCount2 = staffMapper.CountzbrExsit(sta2);
        if(StaExsitCount2==0){
            //return " 原先表格里没有此人,请重新操作 ";
            return Rtn.Error("原先表格里没有此人,请重新操作");
        } else {
            return Rtn.Success(true);
        }



    }

}
