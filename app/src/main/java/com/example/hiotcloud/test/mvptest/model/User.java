package com.example.hiotcloud.test.mvptest.model;

import java.io.Serializable;

public class User implements Serializable {
    private  String userName;
    private  String Password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

}