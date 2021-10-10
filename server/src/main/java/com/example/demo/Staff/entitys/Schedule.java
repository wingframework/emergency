package com.example.demo.Staff.entitys;

import java.time.DateTimeException;
import java.util.Date;

public class Schedule {
    private int id;
    private String leaderStaffId;
    private String peopleStaffId;
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

    public String getLeaderStaffId() {
        return leaderStaffId;
    }

    public void setLeaderStaffId(String leaderStaffId) {
        this.leaderStaffId = leaderStaffId;
    }

    public String getPeopleStaffId() {
        return peopleStaffId;
    }

    public void setPeopleStaffId(String peopleStaffId) {
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
