package com.example.demo.passport.Dto;

import javax.xml.crypto.Data;
import java.util.Date;

public class QuerySentSmsExpire {

    private Date sentAt;
    private String phoneNumber;

    // 构造器？？
    public QuerySentSmsExpire(Date sentAt, String phoneNumber) {
        this.sentAt = sentAt;
        this.phoneNumber = phoneNumber;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }
}
