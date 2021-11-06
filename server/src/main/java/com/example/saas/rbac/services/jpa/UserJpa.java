package com.example.saas.rbac.services.jpa;

import com.example.saas.rbac.entitys.User;
import com.example.saas.common.sevices.jpa.base.BaseRepository;

public interface UserJpa extends BaseRepository<User,Integer> {
    User findByUsername(String username);

}
