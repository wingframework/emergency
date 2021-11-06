package com.example.saas.passport.services;

import com.example.saas.rbac.entitys.User;
import com.example.saas.passport.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User findUserById(int id) {
        return userMapper.FindUserById(id);

    }

    // public User findUserByUsername(String username) {
    // return userMapper.QueryUserByUsername(username);

    // }

}
