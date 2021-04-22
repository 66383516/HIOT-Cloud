package com.example.hiotcloud.test.mvptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hiotcloud.R;
import com.example.hiotcloud.test.fragmenttest.TestFragment;
import com.example.hiotcloud.ui.base.BaseActivity;
import com.example.hiotcloud.test.mvptest.model.User;
import com.example.hiotcloud.ui.main.MainActivity;
import com.example.hiotcloud.utils.SharedPreferencesUtil;

import javax.inject.Inject;

public class TestMVPActivity extends BaseActivity<TestView, TestPresenter> implements TestView{

    @Inject
    TestPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mvp);
        final EditText etUserName = findViewById(R.id.et_user_name);
        final EditText etPassword = findViewById(R.id.et_password);
        final Button btnLogin = findViewById(R.id.btn_login);
        final User user = new User();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment_test,TestFragment.newInstance(R.drawable.test_photo))
                .commit();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUserName(etUserName.getText().toString());
                user.setPassword(etPassword.getText().toString());
                presenter.login(user);
                SharedPreferencesUtil.getInstance(TestMVPActivity.this).setLogin(false);

                //打开登录界面，关闭此界面
                Intent intent = new Intent(TestMVPActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public TestPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
 