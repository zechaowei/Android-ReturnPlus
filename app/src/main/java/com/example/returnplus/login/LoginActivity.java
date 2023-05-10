package com.example.returnplus.login;

import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.example.returnplus.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题行
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);
    }
}