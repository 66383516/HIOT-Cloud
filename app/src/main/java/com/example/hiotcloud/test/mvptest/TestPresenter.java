package com.example.hiotcloud.test.mvptest;

import com.example.hiotcloud.test.mvptest.model.User;

public class TestPresenter {
    private TestView view;

    public TestPresenter(TestView view) {
        this.view = view;
    }

    public void login(User user){
        if ("ddw".equals(user.getUserName()) && "abc".equals(user.getPassword())) {
            view.showMessage("登录成功");
        }else{
            view.showMessage("登录失败");
        }

    }
}
