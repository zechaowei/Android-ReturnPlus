package com.example.returnplus.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.returnplus.R;

public class LoginActivity extends AppCompatActivity {

    private TextView tvForgetPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题行
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        //找到忘记密码这个按钮
        tvForgetPassword = findViewById(R.id.tvForgetPassword);


        //设置布局文件
        setContentView(R.layout.activity_login);


//        //设置点击忘记密码时跳转到对应网站，例如http://www.baidu.coms
//        tvForgetPassword.setText(Html.fromHtml("<a href=\"http://www.baidu.com\">忘记密码？</a>"));
//        tvForgetPassword.setMovementMethod(LinkMovementMethod.getInstance());
    }
}