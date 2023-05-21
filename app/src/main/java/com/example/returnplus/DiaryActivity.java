package com.example.returnplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.returnplus.diary.Diary1Activity;

public class DiaryActivity extends AppCompatActivity {

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置ActionBar   返回键<---
        ActionBar actionBar = getSupportActionBar();
        //设置后退按钮
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);    //实际上是在菜单项目上显示菜单（在左边显示）

        setContentView(R.layout.activity_diary);

        //实现日记页面
        imageView1 = findViewById(R.id.diary_1);
        imageView2 = findViewById(R.id.diary_2);
        imageView3 = findViewById(R.id.diary_3);

        //跳转到第一个日记界面
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiaryActivity.this, Diary1Activity.class);
                Toast.makeText(DiaryActivity.this,"跳转到小确幸界面",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    /**
     * 返回到Main页面
     * DiaryActivity ---> MainActivity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Intent intent = new Intent(DiaryActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return true;
    }


    //实现 新增日记按钮
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //将资源文件(xml形式的菜单内容)渲染到menu中
        getMenuInflater().inflate(R.menu.add_diary,menu);
        return true;
    }
}