package com.example.saas.staff.dto;

import com.example.saas.staff.entitys.Schedule;

import java.util.Date;

               //值班人员排班创建
public class StaffScheduleCreateInput {
    private int id;
    private Schedule morningSchedule;
    private Schedule noonSchedule;
    private Schedule nightSchedule;

    //早班，中班或者晚班
    private String classes;

    private Date creatAt;
    private Date lastUpdateAt;
    private Date date;

    public Schedule getMorningSchedule() {
        return morningSchedule;
    }

    public void setMorningSchedule(Schedule morningSchedule) {
        this.morningSchedule = morningSchedule;
    }

    public Schedule getNoonSchedule() {
        return noonSchedule;
    }

    public void setNoonSchedule(Schedule noonSchedule) {
        this.noonSchedule = noonSchedule;
    }

    public Schedule getNightSchedule() {
        return nightSchedule;
    }

    public void setNightSchedule(Schedule nightSchedule) {
        this.nightSchedule = nightSchedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
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
