package com.example.saas.rbac.services.jpa;

import com.example.saas.rbac.entitys.Menu;
import com.example.saas.common.sevices.jpa.base.BaseRepository;

import java.util.List;

public interface MenuJpa  extends BaseRepository<Menu,Integer> {

    List<Menu>  findByIdIn(List<Integer> ids);

}
