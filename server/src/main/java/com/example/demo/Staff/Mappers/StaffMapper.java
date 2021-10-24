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

    void create(@Param("staff") Staff staff);

    int countStaffExsit(@Param("staff") Staff staff);

    List<Staff> All(String keyword);

    // 通过关键字分页查询  分页的英语单词最好是：paging
    List<Staff> pageQueryByKeyword(StaffQueryInput staffQueryInput);

    int pageQueryByKeywordCount(StaffQueryInput staffQueryInput);

    // 查询不在值班人员中的用户
    List<User> queryNotStaffUser();


    void deleteById(int id);

    void enableById(int id, boolean enable);


//    User FindUserByUsername( String username );
//    void  UpdatePasswordByPhonenumber ( String phonenumber,String password);
//    User FindUserByPhonenumber( String phonenumber );
}
