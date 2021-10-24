package com.example.demo.passport.entitys;

import javax.xml.crypto.Data;
import java.util.Date;

public class User {
    private int id;
    //头像地址的意思
    private String abatarUrl;
    //登录名称
    private String nickname;
    //用户昵称
    private String username;
    //用户单位
    private String userUnit;
    private String phoneNumber;
    private String email;
    private int sex;
    private String rbac;
    private int status;
    private Date lastLoginAt;

    private String password;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getRbac() {
        return rbac;
    }

    public void setRbac(String rbac) {
        this.rbac = rbac;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
