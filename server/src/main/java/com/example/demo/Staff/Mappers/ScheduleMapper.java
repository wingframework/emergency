package com.example.demo.Staff.Mappers;

import com.example.demo.Staff.Dto.StaffScheduleCreateInput;
import com.example.demo.Staff.entitys.Schedule;
import com.example.demo.Staff.entitys.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ScheduleMapper {

    void Create( StaffScheduleCreateInput staffScheduleCreateInput );


     int CountSchedule(StaffScheduleCreateInput staffScheduleCreateInput);




}
