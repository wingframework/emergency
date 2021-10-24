package com.example.demo.Staff.Mappers;

import com.example.demo.Staff.Dto.ScheduleDateGroupByDate;
import com.example.demo.Staff.Dto.StaffScheduleCreateInput;
import com.example.demo.Staff.Dto.StatOutputDto;
import com.example.demo.Staff.entitys.Schedule;
import com.example.demo.Staff.entitys.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface ScheduleMapper {

   void Create( StaffScheduleCreateInput staffScheduleCreateInput );

   List<Date> ScheduleDateGroupByDate(ScheduleDateGroupByDate scheduleDateGroupByDate);
                       // 通过日期的范围来控制 排班和值班人员
   List<StatOutputDto>  scheduleWithStaffByDateRange(ScheduleDateGroupByDate scheduleDateGroupByDate);

   int CountSchedule(StaffScheduleCreateInput staffScheduleCreateInput);
                                  // 这里为啥要加param这个注解? 自己查了一下：@Param注解的作用是给参数命名,参数命名后就能根据名字得到参数值,正确的将参数传入sql语句中
   List<Schedule> selectScheduleByDateRange(@Param("dateRange") ScheduleDateGroupByDate scheduleDateGroupByDate,@Param("classes") String classes);


}
