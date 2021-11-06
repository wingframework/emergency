package com.example.saas.passport.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SentSmsInput {
@Pattern(regexp = "1[3-9]\\d{9}") // 手机正则
    @NotNull(message="手机号必填")
    private String phonenumber;

   // private String code;
   // private Date sentAt;
    // 1是登录短信，2是注册短信，3是忘记密码短信
    private int type;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
