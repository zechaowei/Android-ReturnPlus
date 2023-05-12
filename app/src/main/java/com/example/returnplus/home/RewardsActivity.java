package com.example.returnplus.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.returnplus.R;

/**
 * 积分兑换页面
 */
public class RewardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置ActionBar
        ActionBar actionBar = getSupportActionBar();
        //设置后退按钮
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_rewards);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            Toast.makeText(this,"返回上一页",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RewardsActivity.this, HomeActivity.class);
            //启动意图
            startActivity(intent);
        }
        return true;
    }
}