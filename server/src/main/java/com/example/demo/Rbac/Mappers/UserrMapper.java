package com.example.demo.Rbac.Mappers;

import com.example.demo.Rbac.Dto.UserrQueryInput;
import com.example.demo.Rbac.Entitys.Userr;
import com.example.demo.Rbac.Entitys.UserrRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserrMapper {
    void UserrAdd(@Param("Userr")Userr userr);
    Userr findUserrByUsername(String username);
    void addUserrRoles(List<UserrRole> userrRoles);
    List<Integer> findRoleIdByUserrID(int userrId);
    void deleteByUserrId(int userrId);
    Userr findUserrById(int id);
    void resetPasswordById(int id);
    void deleteById(int id);
    List<Userr> pageQueryByKeyword(UserrQueryInput userrQueryInput);
    int pageQueryByKeywordCount(UserrQueryInput userrQueryInput);


}
