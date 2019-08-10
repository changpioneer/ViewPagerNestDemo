package com.pioneer.viewpagernest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pioneer.viewpagernest.viewpagerdemo.MainViewpagerActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 进入ViewPager嵌套Demo界面
     * @param view
     */
    public void viewPagerNest(View view){
        Intent intent = new Intent(this, MainViewpagerActivity.class);
        startActivity(intent);
    }


}
