package com.example.demo.Rbac.services.jpa;

import com.example.demo.Rbac.Entitys.Menu;
import com.example.demo.commons.sevices.jpa.base.BaseRepository;

import java.util.List;

public interface MenuJpa  extends BaseRepository<Menu,Integer> {

    List<Menu>  findByIdIn(List<Integer> ids);
}
