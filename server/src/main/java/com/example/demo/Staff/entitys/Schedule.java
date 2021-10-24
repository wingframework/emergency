package com.example.demo.Staff.entitys;

import java.util.Date;

public class Schedule {
    private int id;
    private Integer leaderStaffId;
    private Integer peopleStaffId;
    //早班，中班或者晚班
    private String classes;
    private Date createAt=new Date();
    private Date lastUpdateAt=new Date();
    private Date date;


    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getLeaderStaffId() {
        return leaderStaffId;
    }

    public void setLeaderStaffId(Integer leaderStaffId) {
        this.leaderStaffId = leaderStaffId;
    }

    public Integer getPeopleStaffId() {
        return peopleStaffId;
    }

    public void setPeopleStaffId(Integer peopleStaffId) {
        this.peopleStaffId = peopleStaffId;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }



    public Date getLastUpdateAt() {
        return lastUpdateAt;
    }

    public void setLastUpdateAt(Date lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
