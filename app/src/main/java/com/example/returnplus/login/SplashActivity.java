package com.example.returnplus.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

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
        // 跳转到登录界面
        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);

        startActivity(intent);
        finish();
    }
}