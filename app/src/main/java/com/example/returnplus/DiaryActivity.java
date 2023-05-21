package com.example.returnplus;

import android.annotation.SuppressLint;
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
import com.example.returnplus.diary.Diary2Activity;
import com.example.returnplus.diary.Diary3Activity;

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

        imageView1.setOnClickListener(new ImageViewOnClickListener());
        imageView2.setOnClickListener(new ImageViewOnClickListener());
        imageView3.setOnClickListener(new ImageViewOnClickListener());
    }



    //点击事件
    class ImageViewOnClickListener implements View.OnClickListener{
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            //获取点击事件的id
            int id = view.getId();
            //获取ImageView的意图
            Intent intent = null;
            switch (id){
                case R.id.diary_1:
                    //跳转到第一个日记页面----小确幸
                    intent = new Intent(DiaryActivity.this, Diary1Activity.class);
                    Toast.makeText(DiaryActivity.this,"跳转到小确幸界面",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.diary_2:
                    //跳转到第一个日记页面----小确幸
                    intent = new Intent(DiaryActivity.this, Diary2Activity.class);
                    Toast.makeText(DiaryActivity.this,"跳转到中确幸界面",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.diary_3:
                    //跳转到第一个日记页面----小确幸
                    intent = new Intent(DiaryActivity.this, Diary3Activity.class);
                    Toast.makeText(DiaryActivity.this,"跳转到大确幸界面",Toast.LENGTH_SHORT).show();
                    break;
            }
            startActivity(intent);
        }
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
        }else if (item.getItemId() == R.id.diary_add){
            //点击新增日记按钮提示功能暂未开放
            Toast.makeText(DiaryActivity.this,"新增日记功能暂未开放",Toast.LENGTH_SHORT).show();
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