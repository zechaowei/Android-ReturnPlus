package com.example.returnplus.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.returnplus.R;

/**
 * 心理小憩
 */
public class fragment_rest extends Fragment {

    private Button btnRest1;
    private Button btnRest2;
    private Button btnRest3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rest, container, false);
        btnRest1 = view.findViewById(R.id.rest_btn1);
        btnRest2 = view.findViewById(R.id.rest_btn2);
        btnRest3 = view.findViewById(R.id.rest_btn3);

        btnRest1.setOnClickListener(new BtnOnClickListener());
        btnRest2.setOnClickListener(new BtnOnClickListener());
        btnRest3.setOnClickListener(new BtnOnClickListener());

        return view;
    }

    class BtnOnClickListener implements View.OnClickListener{

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            //根据点击的组件
            int id = view.getId();
            //根据按钮顶意图
            Intent intent = null;
            switch (id){
                case R.id.rest_btn1:
                    onButtonClick1(view);
                    break;
                case R.id.rest_btn2:
                    onButtonClick2(view);
                    break;
                case R.id.rest_btn3:
                    onButtonClick3(view);
                    break;
            }
        }
    }

    public void onButtonClick1(View view){
        String url = "http://web.4399.com/h5/stat/xyx.php?target=fxq";   //指定要跳转的网页URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    public void onButtonClick2(View view){
        String url = "https://music.163.com/";   //指定要跳转的网页URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    public void onButtonClick3(View view){
        String url = "https://www.smilingmind.com.au/";   //指定要跳转的网页URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
