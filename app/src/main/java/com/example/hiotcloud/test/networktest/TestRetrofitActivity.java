package com.example.hiotcloud.test.networktest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hiotcloud.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TestRetrofitActivity extends AppCompatActivity {

    private static final String TAG = "TestRetrofitActivity";
    private Retrofit retrofit;
    private TestRetfitService service;
    private Gson gson = new Gson();
    private  EditText etTokenRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);


        //取到edit_token
        etTokenRetrofit = findViewById(R.id.et_token_retrofit);
        //创建retrofit和service对象
        createRetrofit();

        //登录
        final Button btnLogin = findViewById(R.id.btn_retrofit_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("ddw", "abc123", "app");
                //    login2("ddw", "abc123", "app");
            }
        });
        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_retrofit_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userinfo("b994d5d57634420ab0b33a081eddc97d_9f24bab3a13b4e5bb04ca69cbfc96c5d_use");
            }
        });
        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_retrofit_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateemail("b2965e934986416db8f1a46f962b4ad4_2b6db1fa7bae4ba39ee6e43aea3d0025_use",
                        "qqqqq11111@qq.com");
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
        userBean.setUsername("ddswddwddw");
        userBean.setPassword("abc123");
        userBean.setEmail("1qqqq123@qq.com");
        userBean.setUserType("1");
        Call<ResponseBody> call = service.register(userBean);
        callEnqueue(call);
    }

    /**
     * 修改邮箱
     *
     * @param authorization
     */
    private void updateemail(String authorization, String email) {
        Call<ResponseBody> call = service.updateemail(authorization, email);
        callEnqueue(call);
    }

    /**
     * 用户信息
     *
     * @param authorization
     */
    private void userinfo(String authorization) {
        //Call<ResponseBody> call = service.userinfo(authorization);
        //callEnqueueUserInfo(call);

        Call<ResultBase<UserBean>> Call = service.userinfo2(authorization);
        Call.enqueue(new Callback<ResultBase<UserBean>>() {
            @Override
            public void onResponse(retrofit2.Call<ResultBase<UserBean>> call, Response<ResultBase<UserBean>> response) {
                ResultBase<UserBean> resultBase = response.body();
                if (resultBase != null && resultBase.getData() != null) {
                    resultBase.getData();
                    String str = resultBase.getData().getUsername() + "," + resultBase.getData().getEmail();
                    Toast.makeText(TestRetrofitActivity.this, str, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResultBase<UserBean>> call, Throwable t) {

            }
        });
    }

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @param loginCode
     */
    private void login(String userName, String password, String loginCode) {
        Call<ResponseBody> call = service.login(userName, password, loginCode);
        callEnqueueLogin(call);
    }

    /**
     * login2登录
     *
     * @param userName
     * @param password
     * @param loginCode
     */
    private void login2(String userName, String password, String loginCode) {
        Call<ResponseBody> call = service.login2(userName, password, loginCode);
        callEnqueue(call);
    }

    private void createRetrofit() {
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

    private void callEnqueueLogin(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Log.d(TAG, "onResponse: " + response.body().string());
                Type type = new TypeToken<ResultBase<LoginResultDTO>>() {
                }.getType();
                ResultBase<LoginResultDTO> loginResult = gson.fromJson(response.body().toString(), type);
                if (loginResult != null && loginResult.getData() != null) {
                    String token = loginResult.getData().getTokenValue();
                    etTokenRetrofit.setText(token);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void callEnqueueUserInfo(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    //                Log.d(TAG, "onResponse: " + response.body().string());
                    Type type = new TypeToken<ResultBase<UserBean>>(){}.getType();
                    ResultBase<UserBean> resultBase = gson.fromJson(response.body().string(), type);
                    if (resultBase != null && resultBase.getData() != null) {
                        UserBean userBean = resultBase.getData();
                        String str = String.format("用户名:%s，密码:%s，email: %s，用户类型:%s",
                                userBean.getUsername(), userBean.getPassword(), userBean.getEmail(), userBean.getUserType());
                        Toast.makeText(TestRetrofitActivity.this, str, Toast.LENGTH_SHORT).show();
                    }
//                    if (resultBase != null && resultBase.getMsg() != null) {
 //                       Toast.makeText(TestRetrofitActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
 //                   }
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