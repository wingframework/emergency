package com.example.demo.Staff.Mappers;

import com.example.demo.Staff.Dto.StaffQueryInput;
import com.example.demo.Staff.entitys.Staff;
import com.example.demo.passport.entitys.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StaffMapper {

     void Create( @Param("staff") Staff staff );

     int  CountStaffExsit( @Param("staff") Staff staff);

     List<Staff> All(String keyword);


     List<Staff> pageQueryByKeyword(StaffQueryInput staffQueryInput);
     int pageQueryByKeywordCount(StaffQueryInput staffQueryInput);
     List<User> queryNotStaffUser();



     void DeleteById(int id);

     void DisableById(int id,boolean enable );


//    User FindUserByUsername( String username );
//    void  UpdatePasswordByPhonenumber ( String phonenumber,String password);
//    User FindUserByPhonenumber( String phonenumber );
}
