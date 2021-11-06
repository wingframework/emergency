package com.example.saas.staff.dto;

import javax.validation.constraints.NotNull;

public class DisableStaffDto {
    @NotNull(message = "用户名必填")
    private String username;
    @NotNull(message="值班人员姓名必填")
    private String nickname;
    @NotNull(message="只能是值班领导或者普通员工")
    private String job;
    @NotNull(message = "只能是A组或B组或C组或D组")
    private String groupname;
    @NotNull(message = "true是启用，false是禁用")
    private boolean disable;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
