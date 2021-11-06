package com.example.saas.staff.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StaffDto {
    private int id;
    @NotNull(message="用户名必填")
    private String username;

    @NotNull(message="值班人姓名必填")
    private String nickname;

    private String job;

    @NotNull(message="只能是值班领导或者普通员工")
    private String groupname;

    @NotNull(message="手机号必填")
    private String phonenumber;
    private boolean state;
    private String userid;




}
