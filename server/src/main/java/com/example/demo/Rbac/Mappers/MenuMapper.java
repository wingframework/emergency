package com.example.demo.Rbac.Mappers;

import com.example.demo.Rbac.Entitys.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuMapper {
    void insert(@Param("menu") Menu menu);
    void deleteById(int id );
    void updateById(int id);
    void selectById(int id);
    List<Menu> queryAll(Menu menu);

}
