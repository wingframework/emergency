package com.example.demo.Staff.Dto;

import com.example.demo.Staff.entitys.Staff;

import java.util.List;

public class StaffQueryOutput {
   private List<Staff> data;

   private  int total;

    public List<Staff> getData() {
        return data;
    }

    public void setData(List<Staff> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
