package com.example.demo.Cofigs;

// 通用请求响应体 response
public class Rtn<T> {
    public boolean isOk() {
        return ok;
    }

    public  static <T2> Rtn<T2> Success(T2 data){
        Rtn rtn=  new Rtn<T2>();
        rtn.setData(data);
        rtn.setOk(true);
        return rtn;
    }
    public static <T2> Rtn<T2> Error(){
        Rtn rtn=  new Rtn<T2>();
        rtn.setOk(false);
        return rtn;
    }

    public static <T2> Rtn<T2> Error(String msg){
        Rtn rtn=  new Rtn<T2>();
        rtn.setOk(false);
        rtn.setMsg(msg);
        return rtn;
    }


    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private  boolean ok;
    private  T data;
    private  String msg;
}
