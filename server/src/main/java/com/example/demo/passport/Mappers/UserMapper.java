package com.example.demo.passport.Mappers;

import com.example.demo.passport.Dto.SentSmsInput;
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

     void  UpdatePasswordByPhoneNumber ( String phoneNumber,String password);

    User FindUserByPhoneNumber( String phoneNumber );



    User FindUserById(int id);


    User QueryUserByUsername(String username);
}
