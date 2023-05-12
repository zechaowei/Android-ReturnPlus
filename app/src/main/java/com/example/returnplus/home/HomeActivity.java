package com.example.returnplus.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.returnplus.MainActivity;
import com.example.returnplus.R;
import com.example.returnplus.login.LoginActivity;

/**
 * Home页面
 */
public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lv_home;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题行
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        //设置ActionBar
        ActionBar actionBar = getSupportActionBar();
        //设置后退按钮
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_home);

        //找到home按钮
        lv_home = findViewById(R.id.lv_home);

        //定义列表中展示的数据内容
        String[] datas = new String[]{
                "签到", "吃药小提醒", "信息",
                "积分兑换", "设置", "关于我们",
                "个人信息", "退出登录"
        };

        //准备适配器
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);
        //将适配器绑定到ListView
        lv_home.setAdapter(adapter);

        lv_home.setOnItemClickListener(this);
    }

    /**
     * ListView条目点击事件
     * <p>
     * String[] datas = new String[]{
     * "页面1", "页面2", "页面3",
     * "页面4", "页面5", "页面6",
     * "页面7", "页面8", "页面9"
     * };
     *
     * @param parent
     * @param view
     * @param position 代表条目的序号，从0开始   0-1-2-3
     * @param id
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("HomeActivity", "点击的position" + "   " + position + "号");
        Intent intent = null;
        switch (position) {
            case 0:
                //签到界面
                intent = new Intent(this, SignActivity.class);
                break;
            case 1:
                //吃药小提醒
                intent = new Intent(this, MedicineActivity.class);
                break;
            case 2:
                //信息界面
                intent = new Intent(this, MessageActivity.class);
                break;
            case 3:
                //积分兑换界面
                intent = new Intent(this, RewardsActivity.class);
                break;
            case 4:
                //设置界面
                intent = new Intent(this, SettingsActivity.class);
                break;
            case 5:
                //关于界面
                intent = new Intent(this, AboutActivity.class);
                break;
            case 6:
                //个人信息界面
                intent = new Intent(this, PersonActivity.class);
                break;
            case 7:
                //退出界面
                intent = new Intent(this, LoginActivity.class);
                logout();
                break;
        }

        //如果是退出登录，需要结束当前Activity
        if (position == 7) {
            finish();
        }
        startActivity(intent);
    }

    /**
     * 清空配置文件
     */
    private void logout() {
        SharedPreferences sp = getSharedPreferences("AUTO_LOGIN", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.clear();
        edit.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            Toast.makeText(this,"跳转到Main页",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            //启动意图
            startActivity(intent);
        }
        return true;
    }
}