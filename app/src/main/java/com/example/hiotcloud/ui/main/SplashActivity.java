package com.example.hiotcloud.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hiotcloud.R;
import com.example.hiotcloud.test.mvptest.TestMVPActivity;
import com.example.hiotcloud.utils.SharedPreferencesUtil;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final int HANDLER_MSG_OPEN_NEW = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg){
            super.handleMessage(msg);
            boolean hasLogin = false;
            if(msg.what == HANDLER_MSG_OPEN_NEW){
                //根据登录状态给hasLogin赋值
                hasLogin = SharedPreferencesUtil.getInstance(com.example.hiotcloud.ui.main.SplashActivity.this).isLogin();

                //打开列表界面或登陆界面
                Intent intent = null;
                if(hasLogin){
                    //打开列表界面
                    intent = new Intent(com.example.hiotcloud.ui.main.SplashActivity.this,
                            com.example.hiotcloud.ui.main.MainActivity.class);
                }
                else{
                    //打开登录界面
                    intent = new Intent(com.example.hiotcloud.ui.main.SplashActivity.this,
                            com.example.hiotcloud.test.mvptest.TestMVPActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //设置定时器，发消息
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(HANDLER_MSG_OPEN_NEW);
            }
        },3000);
    }
}
