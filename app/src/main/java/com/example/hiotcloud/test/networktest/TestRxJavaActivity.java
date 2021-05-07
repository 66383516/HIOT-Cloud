package com.example.hiotcloud.test.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hiotcloud.R;
import com.example.hiotcloud.data.NetService;
import com.example.hiotcloud.test.mvptest.model.User;

import java.text.BreakIterator;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRxJavaActivity extends AppCompatActivity {

    private static final String TAG = "TestRxJavaActivity";
    private Retrofit retrofit;

    private NetService service;
    private EditText etToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rx_java);

        /**
         * 创建retrofit
         */
        createRetrofit();

        //editText存放token
        etToken = findViewById(R.id.et_rxjava_token);


        //登录
        Button btnLogin = findViewById(R.id.btn_rxjava_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("ddw", "abc123");
            }
        });

        //用户信息
        Button btnUserinfo = findViewById(R.id.btn_rxjava_userinfo);
        btnUserinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInfo(etToken.getText().toString());
            }
        });

        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_rxjava_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmail(etToken.getText().toString(),"1103133133@qq.com");
            }
        });

        //注册
        Button btnRegister = findViewById(R.id.btn_rxjava_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
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
        userBean.setUsername("ddsw123");
        userBean.setPassword("abc123");
        userBean.setEmail("1qqqq12443@qq.com");
        userBean.setUserType("1");
        service.register(userBean).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultBase<UserBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResultBase<UserBean> resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            resultBase.getData();
                            Toast.makeText(TestRxJavaActivity.this, "注册成功 ", Toast.LENGTH_SHORT).show();
                        }else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                            Toast.makeText(TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(), e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 修改邮箱
     * @param authorization
     * @param email
     */
    private void updateEmail(String authorization, String email) {
        service.updateemail(authorization,email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBase<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        
                    }

                    @Override
                    public void onNext(@NonNull ResultBase<String> resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            resultBase.getData();
                            Toast.makeText(TestRxJavaActivity.this, "更改成功 " + resultBase.getData()
                                    , Toast.LENGTH_SHORT).show();
                        }else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                            Toast.makeText(TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(), e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 用户信息
     * @param authorization
     */
    private void getUserInfo(String authorization) {
        Observable<ResultBase<UserBean>> observable = service.userinfo(authorization);
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultBase<UserBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResultBase<UserBean> resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            UserBean userBean = resultBase.getData();
                            String str = String.format("用户：%s，email:%s",
                                    userBean.getUsername(), userBean.getEmail());
                            Toast.makeText(TestRxJavaActivity.this, str, Toast.LENGTH_SHORT).show();
                        } else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                            Toast.makeText(TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(), e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 登录
     * @param userName
     * @param password
     */
    private void login(String userName, String password) {
        service.login(userName, password, "app")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResultBase<LoginResultDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResultBase<LoginResultDTO> resultBase) {
                        if (resultBase != null && resultBase.getData() != null) {
                            LoginResultDTO loginResult = resultBase.getData();
                            Log.d(TAG, "onNext: " + loginResult.getTokenValue());
                            Toast.makeText(TestRxJavaActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            etToken.setText(loginResult.getTokenValue());
                        }else if(resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                            Toast.makeText(TestRxJavaActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage(), e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 创建retrofit
     */
    private void createRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(TestRetfitService.basUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(NetService.class);
    }
}