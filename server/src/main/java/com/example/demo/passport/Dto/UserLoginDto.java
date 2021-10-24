package com.example.demo.passport.Dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserLoginDto {
    @ApiModelProperty("用户名")
    @NotNull(message="用户名必填")
    private String username;
    @NotNull(message="密码必填")
    private String password;
    @Length(min=4,max = 4)
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
