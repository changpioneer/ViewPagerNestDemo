package com.pioneer.viewpagernest.viewpagerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.pioneer.viewpagernest.R;
import com.pioneer.viewpagernest.coustomview.NoScollerViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainViewpagerActivity extends AppCompatActivity {

    private static final String[] TITLES = {"页面一", "页面二"};
    private NoScollerViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_viewpager);
        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);

        //创建tablayout的子Tab
        for (int i = 0; i < TITLES.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(TITLES[i]));
        }

        list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new SencondFragment());
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);

        //关联tablayout和viewpager
        tabLayout.setupWithViewPager(viewPager);
    }


    class MyAdapter extends FragmentPagerAdapter {

        private List<Fragment> list;

        public MyAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }

}
