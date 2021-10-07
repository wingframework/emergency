package com.example.demo.passport.Dto;

import lombok.Data;
import org.apache.logging.log4j.message.Message;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class RegisterInputDto {
    @NotNull(message="手机号必填")
    private  String phonenumber;
    @Length(min=4,max = 4,message = "验证码必须为数字")

    private  String code;

    private  String username;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    private  String password;
    private  String repassword;

}
