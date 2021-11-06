package com.example.saas.staff.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
//@Data
public class AddStaffDto {

    @NotNull(message="用户名必填")
    private String username;
    @NotNull(message="值班人员姓名必填")
    private String nickname;
    @NotNull(message="只能是值班领导或者普通员工")
    private String job;
    @NotNull(message = "只能是A组或B组或C组或D组")
    private String groupname;
    @Length(min = 11,max=11)
    private String phonenumber;
    private String userid;
    private int enable;

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
