package com.example.demo.passport.entitys;

import java.util.Date;

public class Sms {
      private String phonenumber;
      private String code;
      private Date sentAt;
      // 1是登录短信，2是注册短信，3是忘记密码短信
      private int type;

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

      public Date getSentAt() {
            return sentAt;
      }

      public void setSentAt(Date sentAt) {
            this.sentAt = sentAt;
      }

      public int getType() {
            return type;
      }

      public void setType(int type) {
            this.type = type;
      }
}
