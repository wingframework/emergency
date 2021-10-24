//package com.example.demo.Rbac.Mappers;
//
//import com.example.demo.Rbac.Dto.UserrQueryInput;
//import com.example.demo.Rbac.Entitys.User;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Mapper
//@Repository
//public interface UserrMapper {
//    void UserrAdd(@Param("Userr") User userr);
//    User findUserrByUsername(String username);
//    void addUserrRoles(List<UserrRole> userrRoles);
//    List<Integer> findRoleIdByUserrID(int userrId);
//    void deleteByUserrId(int userrId);
//    User findUserrById(int id);
//    void resetPasswordById(int id);
//    void deleteById(int id);
//    List<User> pageQueryByKeyword(UserrQueryInput userrQueryInput);
//    int pageQueryByKeywordCount(UserrQueryInput userrQueryInput);
//
//
//}
