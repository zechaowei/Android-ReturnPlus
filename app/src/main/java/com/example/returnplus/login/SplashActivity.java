package com.example.returnplus.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.returnplus.MainActivity;
import com.example.returnplus.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置全屏模式
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //去掉标题行
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);



        setContentView(R.layout.activity_splash);
        //设置3秒后
        handler.sendEmptyMessageDelayed(0,3000);
    }


    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };

    public void getHome(){

        //判断在SharedPreferences中存在登录信息，则跳转到MainActivity，否则跳转动LoginActivity
        //MODE_PRIVATE默认模式，创建的文件只能由应用程序调用，即为私有的
        SharedPreferences sp = getSharedPreferences("AUTO_LOGIN", Context.MODE_PRIVATE);
        String username = sp.getString("username","");
        String password = sp.getString("password","");


        //TODO 应该判断Token，目前没有网络请求的功能，暂时使用判断用户名、密码代替
        Intent intent = null;
        if(username != null && username.length() > 0
            && password != null && password.length() > 0
        ){
            //MainActivity
            intent = new Intent(this, MainActivity.class);
        }else {
            //LoginActivity
            intent = new Intent(this, LoginActivity.class);
        }

        //启动Activity
        startActivity(intent);
        finish();//避免后退回来
    }
}