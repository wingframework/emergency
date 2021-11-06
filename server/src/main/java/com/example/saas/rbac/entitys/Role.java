package com.example.saas.rbac.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name="rbac_role")
@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roleName;
    // 权限字符
    private String code;
    //排序
    private int sort;
    //1代表正常，0代表不正常
    private int status;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rbac_role_menu", joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "menuId", referencedColumnName = "id")})
    private List<Menu> menuList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
}
