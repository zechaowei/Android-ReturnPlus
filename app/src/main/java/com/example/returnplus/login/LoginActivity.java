package com.example.returnplus.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.returnplus.MainActivity;
import com.example.returnplus.R;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login;

    private TextView tvForgetPassword;
    private TextView userName;
    private TextView passWord;
    private CheckBox autoLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题行
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        //设置布局文件
        setContentView(R.layout.activity_login);

        //找到对应按钮
        btn_login = findViewById(R.id.btn_login);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);
        userName = findViewById(R.id.username);
        passWord = findViewById(R.id.password);
        autoLogin = findViewById(R.id.auto_login);

        //给按钮添加点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("LoginActivity","按钮被点击了，准备登录....");


                String username = userName.getText().toString();
                String password = passWord.getText().toString();

                //TODO 将用户名和密码发送到后段验证
                //admin 12346    userName.getText()---->Editable类型
                if (username ==null ||"".equals(username)){
                    userName.setError("用户名不能为空");
                    return;
                }
                if (password == null || "".equals(password)){
                    passWord.setError("密码不能为空");
                    return;
                }
                if (!"admin".equals(username) || !"123456".equals(password)){
                    userName.setError("用户名或密码不正确");
                    return;
                }

                saveAutoLogin();

                //构造意图，从当前Activity跳转到MainActivity页面
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                //关闭当前Activity
                finish();
                //使用意图启动Activity
                startActivity(intent);
            }
        });



//        //设置点击忘记密码时跳转到对应网站，例如http://www.baidu.coms
//        tvForgetPassword.setText(Html.fromHtml("<a href=\"http://wwww.baidu.com\">忘记密码？</a>"));
//        tvForgetPassword.setMovementMethod(LinkMovementMethod.getInstance());
    }

    //自动登录的保存
    private void saveAutoLogin() {
        if (autoLogin.isChecked()){
            Log.d("LoginActivity","自动登录");

            String username = userName.getText().toString();
            String password = passWord.getText().toString();

            //MODE_PRIVATE默认模式，创建的文件只能由应用程序调用，即为私有的
            SharedPreferences sp = getSharedPreferences("AUTO_LOGIN", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();

            //将数据写入到内存当中
            edit.putString("username",username);
            edit.putString("password",password);

            //将数据写入到文件中
            edit.commit();

        }else {
            //打印日志，可以不用
            Log.d("LoginActivity","非自动登录");
        }
    }
}