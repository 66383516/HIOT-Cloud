package com.example.hiotcloud.test.networktest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hiotcloud.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.hiotcloud.test.networktest.UserBean.token;

public class TestRetrofitActivity extends AppCompatActivity {

    private static final String TAG = "TestRetrofitActivity";
    private Retrofit retrofit;
    private TestRetfitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);


        //创建retrofit和service对象
        createRetrofit() ;

        //登录
        final Button btnLogin = findViewById(R.id.btn_retrofit_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //login("ddw", "abc123", "app");
                login2("ddw", "abc123", "app");
            }
        });
        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_retrofit_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userinfo(token);
            }
        });
        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_retrofit_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateemail(token,
                        "qqqq11111@qq.com");
            }
        });
        //注册
        Button btnregister = findViewById(R.id.btn_retrofit_register);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    /**
     * 用户注册
     */
    private void register() {
        UserBean userBean = new UserBean();
        userBean.setUsername("ddwddwddw");
        userBean.setPassword("abc123");
        userBean.setEmail("1qqqq13@qq.com");
        userBean.setUserType("1");
        Call<ResponseBody> call = service.register(userBean);
        callEnqueue(call);
    }

    /**
     * 修改邮箱
     * @param authorization
     */
    private void updateemail(String authorization,String email) {
        Call<ResponseBody> call = service.updateemail(authorization,email);
        callEnqueue(call);
    }

    /**
     * 用户信息
     * @param authorization
     */
    private void userinfo(String authorization) {
        Call<ResponseBody> call = service.userinfo(authorization);
        callEnqueue(call);
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @param loginCode
     */
    private void login(String userName, String password, String loginCode) {
        Call<ResponseBody> call = service.login(userName, password, loginCode);
        callEnqueue(call);
    }

    /**
     * login2登录
     * @param userName
     * @param password
     * @param loginCode
     */
    private void login2(String userName, String password, String loginCode) {
        Call<ResponseBody> call = service.login2(userName, password, loginCode);
        callEnqueue(call);
    }

    private void createRetrofit(){
        retrofit = new Retrofit.Builder().baseUrl(TestRetfitService.basUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(TestRetfitService.class);
    }

    private void callEnqueue(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }
}