package com.example.returnplus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.returnplus.diary.Diary1Activity;
import com.example.returnplus.diary.Diary2Activity;
import com.example.returnplus.diary.Diary3Activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DiaryActivity extends AppCompatActivity {

    //设置三个状态码
    private static final int REQUEST_CODE_DIARY1 = 1;
    private static final int REQUEST_CODE_DIARY2 = 2;
    private static final int REQUEST_CODE_DIARY3 = 3;

    private TextView mTextView_1;
    private Button mButtonEnter_1;

    private TextView mTextView_2;
    private Button mButtonEnter_2;

    private TextView mTextView_3;
    private Button mButtonEnter_3;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置ActionBar   返回键<---
        ActionBar actionBar = getSupportActionBar();
        //设置后退按钮
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);    //实际上是在菜单项目上显示菜单（在左边显示）

        setContentView(R.layout.activity_diary);


        //保存第一个日记
        mTextView_1 = findViewById(R.id.textview_time_1);
        mButtonEnter_1 = findViewById(R.id.button_enter_1);

        //保存第二个日记
        mTextView_2 = findViewById(R.id.textview_time_2);
        mButtonEnter_2 = findViewById(R.id.button_enter_2);

        //保存第三个日记
        mTextView_3 = findViewById(R.id.textview_time_3);
        mButtonEnter_3 = findViewById(R.id.button_enter_3);

        // 设置按钮点击事件监听器
        mButtonEnter_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryActivity.this, Diary1Activity.class);
                intent.putExtra("prefs_name", "diary1_prefs");
                startActivityForResult(intent, REQUEST_CODE_DIARY1);
            }
        });
        mButtonEnter_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryActivity.this, Diary2Activity.class);
                intent.putExtra("prefs_name", "diary2_prefs");
                startActivityForResult(intent, REQUEST_CODE_DIARY2);
            }
        });
        mButtonEnter_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryActivity.this, Diary3Activity.class);
                intent.putExtra("prefs_name", "diary3_prefs");
                startActivityForResult(intent, REQUEST_CODE_DIARY3);
            }
        });

        // 加载之前保存的时间戳
        SharedPreferences prefs1 = getSharedPreferences("diary1_prefs", MODE_PRIVATE);
        String content1 = prefs1.getString("content", "");
        long timestamp1 = prefs1.getLong("timestamp", System.currentTimeMillis());
        mTextView_1.setText(formatTimestamp(timestamp1));

        SharedPreferences prefs2 = getSharedPreferences("diary2_prefs", MODE_PRIVATE);
        String content2 = prefs2.getString("content", "");
        long timestamp2 = prefs2.getLong("timestamp", System.currentTimeMillis());
        mTextView_2.setText(formatTimestamp(timestamp2));

        SharedPreferences prefs3 = getSharedPreferences("diary3_prefs", MODE_PRIVATE);
        String content3 = prefs3.getString("content", "");
        long timestamp3 = prefs3.getLong("timestamp", System.currentTimeMillis());
        mTextView_3.setText(formatTimestamp(timestamp3));
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

    //保存日记

    /**
     * TODO 实现每个日记页面的修改时间，目前三个日记的修改时间是同步的
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 根据请求码更新对应的时间戳
        switch (requestCode) {
            case REQUEST_CODE_DIARY1:
                if (resultCode == RESULT_OK) {
                    long timestamp = data.getLongExtra("timestamp", System.currentTimeMillis());
                    mTextView_1.setText(formatTimestamp(timestamp));
                }
                break;
            case REQUEST_CODE_DIARY2:
                if (resultCode == RESULT_OK) {
                    long timestamp = data.getLongExtra("timestamp", System.currentTimeMillis());
                    mTextView_2.setText(formatTimestamp(timestamp));
                }
                break;
            case REQUEST_CODE_DIARY3:
                if (resultCode == RESULT_OK) {
                    long timestamp = data.getLongExtra("timestamp", System.currentTimeMillis());
                    mTextView_3.setText(formatTimestamp(timestamp));
                }
                break;
        }
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}