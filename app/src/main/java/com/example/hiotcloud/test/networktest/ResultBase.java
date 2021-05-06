package com.example.hiotcloud.test.networktest;

import java.io.Serializable;

public class ResultBase<T> implements Serializable {

    T data;

    String msg;

    int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
