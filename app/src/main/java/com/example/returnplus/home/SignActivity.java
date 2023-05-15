package com.example.returnplus.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.returnplus.R;

/**
 * 签到页面
 */
public class SignActivity extends AppCompatActivity {
    
    private Button btn_sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置ActionBar
        ActionBar actionBar = getSupportActionBar();
        //设置后退按钮
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_sign);
        
        btn_sign = findViewById(R.id.btn_sign);
        
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignActivity.this, "签到成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            Toast.makeText(this,"返回上一页",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignActivity.this, HomeActivity.class);
            //启动意图
            startActivity(intent);
        }
        return true;
    }
}