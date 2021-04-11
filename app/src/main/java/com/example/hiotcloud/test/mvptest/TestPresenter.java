package com.example.hiotcloud.test.mvptest;

import com.example.hiotcloud.base.BasePresenter;
import com.example.hiotcloud.test.dagger2test.ThirdObj;
import com.example.hiotcloud.test.mvptest.model.User;

import javax.inject.Inject;

public class TestPresenter extends BasePresenter<TestView> {

    @Inject
    ThirdObj thirdObj;


    @Inject
    public TestPresenter() {
    }

    public void login(User user) {
        thirdObj.thirdAction();
        if ("ddw".equals(user.getUserName()) && "abc".equals(user.getPassword())) {
            getView().showMessage("登录成功");
        } else {
            getView().showMessage("登录失败");
        }

    }
}
