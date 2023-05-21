package com.example.returnplus.diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.returnplus.DiaryActivity;
import com.example.returnplus.R;

public class Diary3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置ActionBar   返回键<---
        ActionBar actionBar = getSupportActionBar();
        //设置后退按钮
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);    //实际上是在菜单项目上显示菜单（在左边显示）


        setContentView(R.layout.activity_diary3);
    }

    /**
     * 从小确幸日记页面跳转到日记主页面
     * Diary3Activity ---> DiaryActivity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent intent = new Intent(Diary3Activity.this, DiaryActivity.class);
            startActivity(intent);
        }
        return true;
    }
}