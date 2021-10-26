package com.example.demo.Rbac.Dto;

public class RoleQueryInputDto {
    private String keyword;
    private int page=0;
    private int pagesize=10;
    public int skip(){
        return page*pagesize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }
}
