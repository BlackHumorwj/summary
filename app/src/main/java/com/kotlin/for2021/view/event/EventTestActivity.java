package com.kotlin.for2021.view.event;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.kotlin.for2021.R;
import com.kotlin.for2021.TestFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

/**
 * @author : kingBoy
 * @time 2021/6/2 18:18
 */
public class EventTestActivity extends AppCompatActivity {
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, EventTestActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

       SwipeRefreshLayout swipe =  findViewById(R.id.swipe);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return TestFragment.newInstance();
            }

            @Override
            public int getCount() {
                return 5;
            }
        });

        swipe.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(false);
            }
        },5000);
    }

    //顶层分发  false/true 直接消费
    //super 会继续进行分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


    //子View 分发方法返回
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
