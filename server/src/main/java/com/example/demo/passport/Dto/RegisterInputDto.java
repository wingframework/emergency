package com.example.demo.passport.Dto;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class RegisterInputDto {
    @NotNull(message="手机号必填")
    private String phoneNumber;
    @Length(min=4,max = 4)
    private String code;
    @NotNull(message="用户名必填")
    private String username;
    @NotNull(message="密码必填")
    private String password;
      // Re:renewedly,重新地，再度
    private String rePassword;
    private  int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
