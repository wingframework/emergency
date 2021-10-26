package com.example.demo.Rbac.services.jpa;

import com.example.demo.Rbac.Entitys.Menu;
import com.example.demo.Rbac.Entitys.Role;
import com.example.demo.commons.sevices.jpa.base.BaseRepository;

import java.util.List;

public interface RoleJpa  extends BaseRepository<Role,Integer> {

    List<Role> findByIdIn(List<Integer> ids);


}
