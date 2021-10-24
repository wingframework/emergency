package com.example.demo.Rbac.Dto;

import java.util.List;

public class UserrUpdateDto {
    private int id;
    private String abatarUrl;
    private String nickname;
    private String username;
    private String userUnit;
    private String phoneNumber;
    private String email;
    private String rbac;
    private int sex;
    private int status;
    // 职能
    private String func;
    private List<Integer> roleIdList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbatarUrl() {
        return abatarUrl;
    }

    public void setAbatarUrl(String abatarUrl) {
        this.abatarUrl = abatarUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserUnit() {
        return userUnit;
    }

    public void setUserUnit(String userUnit) {
        this.userUnit = userUnit;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRbac() {
        return rbac;
    }

    public void setRbac(String rbac) {
        this.rbac = rbac;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
