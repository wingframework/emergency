package com.example.saas.staff.controllers;

import com.example.saas.configs.Rtn;
import com.example.saas.staff.dto.ScheduleDateGroupByDate;
import com.example.saas.staff.dto.StaffScheduleCreateInput;
import com.example.saas.staff.dto.StatOutputDto;
import com.example.saas.staff.mappers.ScheduleMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    // 统计传入月份的排班计划 schedul   stat是statistic的简写，统计数据的意思
    @PostMapping("stat")
     public Rtn<List<StatOutputDto>> stat(Date date){
                                        //   为什么要用this？
          // SchedulController controller = new SchedulController();
         // 这个controller就是这个this
        ScheduleDateGroupByDate query=    this.getDateRangeByMonthDate(date);
        // ScheduleDateGroupByDate query=    Schedul.getDateRangeByMonthDate(date);

        return   Rtn.Success(scheduleMapper.scheduleWithStaffByDateRange(query));
     }

//     @GetMapping("statExt")
//     public Rtn<List<Schedule>>  statExt(Date date, String classes){
//         ScheduleDateGroupByDate query=    this.getDateRangeByMonthDate(date);
//         // 查出了 对应月份的所有排班
//       List scheduleList=  scheduleMapper.selectScheduleByDateRange(query,classes);
//       Map<String,Schedule> result= new HashMap<String,Schedule>();
////       for(int i=0;i<scheduleList.size();i++){
////           Schedule currentSchedule=  scheduleList.get(i);
////         SimpleDateFormat sdf=   new  SimpleDateFormat(",%y%M%d");
////
////         String key= sdf.format(currentSchedule.getDate());
////         if (result.get(key)!=null){
////             result.
////         }
////
////       }
//        return Rtn.Success();
//     }


        // 需求是写一个月的排班表，从1号到30号，或者从1号到31号
      // 返回的结果是 开始日期和结束日期 比如说是10月1号 和 11月1号 相当于这段代码的作用是写了一个月的排班表
         //通过当前日期对排班日期进行分组 // 通过月的日期来获得日期的区间范围
    private  ScheduleDateGroupByDate getDateRangeByMonthDate(Date date){
        // calendar:日历 格里高利日历算法   range:区间，范围
        Calendar calendar=  new GregorianCalendar();
        calendar.setTime(date);
                               //一般都是对象 点 方法（）；这里好像不太一样
        int year= calendar.get(Calendar.YEAR);
        int month =calendar.get(Calendar.MONTH);
        // query  start date 设置传入的月初
       // calendar.set(year,month,1,0,0,0);
          calendar.set(year,month,1,0,0,0);

        Date startDate=calendar.getTime();
          // 添加一个月的意思          // amount:数量、数额
     //   calendar.add(calendar.MONTH,1);
        calendar.add(calendar. MONTH,1);
        Date endDate=calendar.getTime();
        ScheduleDateGroupByDate query= new ScheduleDateGroupByDate();
        query.setStartDate(startDate);
        query.setEndDate(endDate);
        return query;
    }
}
