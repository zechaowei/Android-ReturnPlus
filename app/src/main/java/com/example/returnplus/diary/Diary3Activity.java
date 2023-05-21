package com.example.returnplus.diary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.returnplus.DiaryActivity;
import com.example.returnplus.R;

public class Diary3Activity extends AppCompatActivity {



    private EditText mEditText_33;
    private Button mButtonSave_33;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置ActionBar   返回键<---
        ActionBar actionBar = getSupportActionBar();
        //设置后退按钮
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);    //实际上是在菜单项目上显示菜单（在左边显示）


        setContentView(R.layout.activity_diary3);

        mEditText_33 = findViewById(R.id.editView_time_33);
        mButtonSave_33 = findViewById(R.id.button_save_33);
        mButtonSave_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 将文本内容保存至 SharedPreferences
                SharedPreferences prefs = getSharedPreferences("diary3_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("content", mEditText_33.getText().toString());
                editor.putLong("timestamp", System.currentTimeMillis());
                editor.apply();

                // 发送结果 Intent 并关闭当前 Activity
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        // 加载之前保存的文本内容
        SharedPreferences prefs = getSharedPreferences("diary3_prefs", MODE_PRIVATE);
        String content = prefs.getString("content", "");
        mEditText_33.setText(content);


        // 监听 EditText 内容变化
        mEditText_33.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // 文本发生变化时，更新保存按钮文本
                if (s.length() > 0) {
                    mButtonSave_33.setText("保存");
                } else {
                    mButtonSave_33.setText("取消");
                }
            }
        });
    }



    //返回到DiaryActivity页面
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(Diary3Activity.this, DiaryActivity.class);
            //启动意图
            startActivity(intent);
        }
        return true;
    }
}