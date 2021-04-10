package com.example.hiotcloud.test.mvptest;

import com.example.hiotcloud.base.BasePresenter;
import com.example.hiotcloud.test.mvptest.model.User;

public class TestPresenter extends BasePresenter<TestView> {

    public TestPresenter() {
    }

    public void login(User user) {
        if ("ddw".equals(user.getUserName()) && "abc".equals(user.getPassword())) {
            getView().showMessage("登录成功");
        } else {
            getView().showMessage("登录失败");
        }

    }
}
