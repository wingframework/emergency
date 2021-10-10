package com.example.demo.passport.Dto;

import lombok.Data;
import org.apache.logging.log4j.message.Message;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class RegisterInputDto {

    @NotNull(message="手机号必填")
    private  String phoneNumber;

     //  @Length(min=4,max = 4,message = "验证码必须为数字")
    @Length(min=4,max = 4)
    private  String code;

    private  String username;
    private  String password;

    //Re:renewedly,重新地，再度
    private  String rePassword;

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
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





}
