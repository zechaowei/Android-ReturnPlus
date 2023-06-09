package com.example.returnplus.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.returnplus.BubbleActivity;
import com.example.returnplus.DiaryActivity;
import com.example.returnplus.DiscussionActivity;
import com.example.returnplus.R;


/**
 * 心间广场
 */
public class fragment_square extends Fragment {
    private Button btn_bubble;
    private Button btn_diary;
    private Button btn_discussion;
    private  Button btn_send;
    private EditText mEditText;


    protected Intent intent;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_square, container, false);
        btn_bubble = view.findViewById(R.id.btn_bubble);
        btn_diary = view.findViewById(R.id.btn_diary);
        btn_discussion = view.findViewById(R.id.btn_discussion);

        btn_send = view.findViewById(R.id.btn_send);
        mEditText = view.findViewById(R.id.mEditText);




        btn_bubble.setOnClickListener(new BtnOnClickListener());
        btn_diary.setOnClickListener(new BtnOnClickListener());
        btn_discussion.setOnClickListener(new BtnOnClickListener());

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fragment_square","发送成功");
            }
        });

        return view;
    }

    class BtnOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //根据点击的组件
            int id = view.getId();
            //根据按钮顶意图
            Intent intent = null;
            switch (id) {
                case R.id.btn_bubble:
                    intent = new Intent(getActivity(), BubbleActivity.class);
                    break;
                case R.id.btn_diary:
                    intent = new Intent(getActivity(), DiaryActivity.class);
                    break;
                case R.id.btn_discussion:
                    intent = new Intent(getActivity(), DiscussionActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
