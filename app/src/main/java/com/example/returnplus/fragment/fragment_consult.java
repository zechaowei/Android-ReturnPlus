package com.example.returnplus.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.returnplus.R;
import com.example.returnplus.fragment.consult.fragment_anchor;
import com.example.returnplus.fragment.consult.fragment_video;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 心理咨询
 */
public class fragment_consult extends Fragment {


    private TabLayout tabLayout_consult;
    private ViewPager viewPager_consult;

    List<Fragment> fragments_consult = new ArrayList<>();
    String tabTitles_consult[] = {"主播", "视频"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consult, container, false);

        // 填充选项内容
        fragments_consult.add(new fragment_anchor());
        fragments_consult.add(new fragment_video());

        // 设置选项卡
        tabLayout_consult = view.findViewById(R.id.tab_consult);
        for (String title : tabTitles_consult) {
            tabLayout_consult.addTab(tabLayout_consult.newTab().setText(title));
        }

        // 设置ViewPager的适配器
        viewPager_consult = view.findViewById(R.id.viewPager_consult);
        viewPager_consult.setAdapter(new FragmentPagerAdapter_consult(getChildFragmentManager()));

        // 将TabLayout与ViewPager关联起来
        tabLayout_consult.setupWithViewPager(viewPager_consult);

        // 设置Tab选中监听器，当选项被选中时，切换到对应的Fragment
        tabLayout_consult.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 当选项被选中时，切换到对应的Fragment
                viewPager_consult.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    class FragmentPagerAdapter_consult extends FragmentPagerAdapter {

        public FragmentPagerAdapter_consult(@NonNull FragmentManager fm) {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments_consult.get(position);
        }

        @Override
        public int getCount() {
            return tabTitles_consult.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles_consult[position];
        }
    }
}