package com.example.demo.passport.Mappers;

import com.example.demo.passport.entitys.Sms;
import com.example.demo.passport.entitys.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    // 起别名的作用，方便在sql使用
    void create(@Param("user") User user);

    int countUserExsit(@Param("user") User user);

    User findUserByUsername(String username);

    void updatePasswordByPhoneNumber(String phoneNumber, String password);

    void deleteById(int id);

    User FindUserById(int id);

}
