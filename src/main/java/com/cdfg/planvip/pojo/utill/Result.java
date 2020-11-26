package com.cdfg.planvip.pojo.utill;

public class Result<T> {

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    private int retcode;
    private String msg;
    private T data;

    public Result(int retcode, String msg, T data) {
        this.retcode = retcode;
        this.msg = msg;
        this.data = data;
    }

    public Result() {

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
