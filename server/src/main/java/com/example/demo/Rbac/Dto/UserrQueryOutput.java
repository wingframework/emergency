package com.example.demo.Rbac.Dto;

import com.example.demo.Rbac.Entitys.User;

import java.util.List;

public class UserrQueryOutput {
    private List<User> data;

    private  int total;

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
