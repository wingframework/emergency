package com.example.saas.rbac.services.jpa;

import com.example.saas.rbac.entitys.Role;
import com.example.saas.common.sevices.jpa.base.BaseRepository;

import java.util.List;

public interface RoleJpa  extends BaseRepository<Role,Integer> {

    List<Role> findByIdIn(List<Integer> ids);


}
