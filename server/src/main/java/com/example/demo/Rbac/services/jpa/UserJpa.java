package com.example.demo.Rbac.services.jpa;

import com.example.demo.Rbac.Entitys.User;
import com.example.demo.commons.sevices.jpa.base.BaseRepository;

public interface UserJpa extends BaseRepository<User,Integer> {
    User findByUsername(String username);

}
