package com.example.returnplus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.returnplus.fragment.fragment_consult;
import com.example.returnplus.fragment.fragment_knowledge;
import com.example.returnplus.fragment.fragment_rest;
import com.example.returnplus.fragment.fragment_square;
import com.example.returnplus.home.HomeActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;




    List<Fragment> fragments = new ArrayList<>();
    String tabTitles[] = {"心间广场", "心理咨询", "心理知识", "心理小憩"};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题行
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);


        //设置ActionBar   返回键<---
        ActionBar actionBar = getSupportActionBar();
        //设置后退按钮
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);    //实际上是在菜单项目上显示菜单（在左边显示）
        //设置actionBar的左边图标，设置为自定义图标
        actionBar.setHomeAsUpIndicator(R.drawable.icon_home);


        setContentView(R.layout.activity_main);



        //设置选项卡
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);

        //填充选项内容
        fragments.add(new fragment_square());
        fragments.add(new fragment_consult());
        fragments.add(new fragment_knowledge());
        fragments.add(new fragment_rest());


        //将ViewPager和TabLayout关联
        //设置ViewPager之后，Tab的选项内容由ViewPager（适配器）提供
        tabLayout.setupWithViewPager(viewPager);

        //FragmentManager用于Fragment 什么时候开始展示，什么时候开始销毁
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
    }


    //适配器
    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        //构造器
        public MyFragmentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, MyFragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        //关联之后的TabLayout中题目(Item)，有ViewPager的适配器方法getPageTitle提供
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

    //重写方法，监听菜单中的选项
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            Toast.makeText(this, "跳转到Home页", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            //启动意图
            startActivity(intent);
        }
        return true;
    }
}