package com.example.demo.Staff.Controllers;

import com.example.demo.AutoMapper;
import com.example.demo.Cofigs.Rtn;
import com.example.demo.Staff.Dto.*;
import com.example.demo.Staff.Mappers.ScheduleMapper;
import com.example.demo.Staff.entitys.Staff;
import com.example.demo.Staff.Mappers.StaffMapper;
import com.example.demo.passport.entitys.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation("（值班人员）")
@RestController
@RequestMapping("api/staff")
public class StaffController {
    @Autowired
    private StaffMapper staffMapper;


    //通过id删除值班人员
    @DeleteMapping("delete/{id}")      //@PathVariable 接收请求路径中占位符的值
    public Rtn<Boolean> DeleteStaffById(@PathVariable int id){
        staffMapper.DeleteById(id);
        return Rtn.Success(true);
    }

    //通过id启用或者禁用值班人员
    @GetMapping("disable/{id}")
    public Rtn<Boolean> DisableStaffById(@PathVariable int id,boolean enable){
        staffMapper.DisableById(id,enable);
        return Rtn.Success(true);
    }

    //    分页查询值班值守人员   StaffQueryOutput:查询值班值守人员输出给前端  //

//    @GetMapping("list")
//    public Rtn<StaffQueryOutput> GetStaff(StaffQueryInput staffQueryInput){
//        List<Staff> data= staffMapper.pageQueryByKeyword(staffQueryInput); //page在这里是分页的意思,通过关键字 分页 查询
//        int total= staffMapper.pageQueryByKeywordCount(staffQueryInput);
//      StaffQueryOutput result=  new StaffQueryOutput();  //创建了一个查询值班人员  分页结果的对象
//      result.setData(data);
//      result.setTotal(total);
//      return Rtn.Success(result);
//    }

     @GetMapping("list")
     public Rtn<StaffQueryOutput> GetStaff(StaffQueryInput staffQueryInput){
       List<Staff> data = staffMapper.pageQueryByKeyword(staffQueryInput);
      int total = staffMapper.pageQueryByKeywordCount(staffQueryInput);
      StaffQueryOutput result  = new StaffQueryOutput();
      result.setData(data);
      result.setTotal(total);
      return Rtn.Success(result);

     }



    @ApiOperation("查询可加入的值班人员的用户列表")
    @GetMapping("queryNotStaffUser")
                           //查询不是值班人员的用户
    public Rtn<List<User>> queryNotStaffUser() {

      List<User> list=  staffMapper.queryNotStaffUser();

     return Rtn.Success(list);
    }



    //添加值班人员
    @PostMapping("Create")
    public Rtn<Boolean> AddStaff(@RequestBody AddStaffDto addStaffDto){

        Staff sta1 = AutoMapper.INSTANCE.AddDtoToStaff(addStaffDto);

        int  StaffExsitCount1 = staffMapper.CountStaffExsit(sta1);


        if( StaffExsitCount1>0 ){

            return Rtn.Error("你输入的用户名或者名称已被他人使用");
        }
        staffMapper.Create(sta1);
        return Rtn.Success(true);
    }

    //修改值班人员
    @PostMapping("Update")
    public Rtn<Boolean> UpdateStaff(@RequestBody UpdateStaffDto updateStaffDto){

        Staff sta2 = AutoMapper.INSTANCE.UpdateDtoToStaff(updateStaffDto);
        int  StaExsitCount2 = staffMapper.CountStaffExsit(sta2);
        if(StaExsitCount2==0){

            return Rtn.Error("原先表格里没有此人,请重新操作");
        } else {
            return Rtn.Success(true);
        }
    }



    }




