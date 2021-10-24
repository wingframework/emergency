package com.example.demo.Rbac.Dto;

import com.example.demo.Rbac.Entitys.Userr;

import java.util.List;

public class UserrQueryOutput {
    private List<Userr> data;

    private  int total;

    public List<Userr> getData() {
        return data;
    }

    public void setData(List<Userr> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
