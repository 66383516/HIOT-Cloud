package com.example.hiotcloud.test.networktest;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hiotcloud.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.BatchUpdateException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * okhttp框架测试类
 */
public class TestOkHttpActivity extends AppCompatActivity {

    private static final String basUrl = "http://114.67.88.191:8080";
    private static final String TAG = "TestOkHttpActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ok_http);

        //登录
        Button btnLogin = findViewById(R.id.btn_okhttp_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login("zcy", "abc123", "app");
            }
        });

        //获取用户信息
        Button btnUserInfo = findViewById(R.id.btn_okhttp_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo("fac7545d8b574eb6a36a9b3dd55f2ebe_cfa975b867a54e5b892ce5be335ea64f_use");
            }
        });

        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_okhttp_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateEmail("fac7545d8b574eb6a36a9b3dd55f2ebe_cfa975b867a54e5b892ce5be335ea64f_use",
                            "180308011233@qq.com");
            }
        });

        //跳转
        Button btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(TestOkHttpActivity.this,TestRetrofitActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 修改邮箱
     * @param authorization
     * @param email
     */
    private void UpdateEmail(String authorization, String email) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder().build();
        String url = basUrl + "/user/email?email=" + email;
        Request request = new Request.Builder().put(body).url(url).header("Authorization", authorization).build();
        callEnqueue(okHttpClient, request);
    }


    /**
     * 用户信息
     * @param authorization
     */
    private void getUserInfo(String authorization) {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = basUrl + "/user/one";
        Request request = new Request.Builder().get().url(url).header("Authorization", authorization).build();
        callEnqueue(okHttpClient, request);
    }

    /**
     * 登录测试
     * @param userName
     * @param password
     * @param loginCode
     */
    private void Login(String userName, String password, String loginCode) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder().build();
        String url = basUrl + String.format("/auth/login?username=%s&password=%s&loginCode=%s",
                userName, password, loginCode);
        Request request = new Request.Builder().post(body).url(url).build();
        callEnqueue(okHttpClient, request);
    }

    private void callEnqueue(OkHttpClient okHttpClient, Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage(), e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }
}