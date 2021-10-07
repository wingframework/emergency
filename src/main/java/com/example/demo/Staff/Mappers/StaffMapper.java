package com.example.demo.Staff.Mappers;

import com.example.demo.Staff.Dto.StaffQueryInput;
import com.example.demo.Staff.entitys.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StaffMapper {

     void Create( @Param("staff") Staff staff );

     int  CountzbrExsit( @Param("staff") Staff staff);
     List<Staff> All(String keyword);

     List<Staff>  pageQueryByKeyword(StaffQueryInput staffQueryInput);
     int pageQueryByKeywordCount(StaffQueryInput staffQueryInput);


     void DeleteById(int id);
     void DisableById(int id);


//    User FindUserByUsername( String username );
//    void  UpdatePasswordByPhonenumber ( String phonenumber,String password);
//    User FindUserByPhonenumber( String phonenumber );
}
