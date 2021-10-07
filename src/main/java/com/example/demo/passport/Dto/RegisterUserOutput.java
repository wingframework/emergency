package com.example.demo.passport.Dto;

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
