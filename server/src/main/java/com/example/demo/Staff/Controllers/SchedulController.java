package com.example.demo.Staff.Controllers;

import com.example.demo.Cofigs.Rtn;
import com.example.demo.Staff.Dto.StaffScheduleCreateInput;
import com.example.demo.Staff.Mappers.ScheduleMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ApiOperation("排班")
@RestController
@RequestMapping("api/schedule")
public class SchedulController {

    @Autowired //实现引用类型的赋值
    private ScheduleMapper scheduleMapper;

    @PostMapping("StaffScheduleCreate")
    public Rtn<Boolean> StaffScheduleCreate(@RequestBody StaffScheduleCreateInput staffScheduleCreateInput) {

        scheduleMapper.Create(staffScheduleCreateInput);
        return Rtn.Success(true);

    }
}
