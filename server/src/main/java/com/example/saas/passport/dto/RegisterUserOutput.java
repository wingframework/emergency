package com.example.saas.passport.dto;

public class RegisterUserOutput {
    private  String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RegisterUserOutput(String token){
        this.token=token;
    }
}
