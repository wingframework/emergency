package com.example.saas.rbac.dto;

import com.example.saas.rbac.entitys.Role;

import java.util.List;

public class RoleQueryOutput {
    private List<Role> data;

    private  int total;

    public List<Role> getData() {
        return data;
    }

    public void setData(List<Role> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
