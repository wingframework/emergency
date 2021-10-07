package com.example.demo.passport.Mappers;

import com.example.demo.passport.entitys.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

  //  User Sel(int id);
   // List<User> All();
   // void DeleteById(int id);
    void Create( @Param("user") User user );

    int  CountUserExsit( @Param("user") User user);
     User FindUserByUsername( String username );
     void  UpdatePasswordByPhonenumber ( String phonenumber,String password);
    User FindUserByPhonenumber( String phonenumber );

    //登录有关的
    public User queryUserByUsername(String username);
    public  User findUserById(int id);












}
