package com.example.saas.passport.dto;

public class LoginUserOutput {
    private  String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginUserOutput(String token){
        this.token=token;
    }
}
