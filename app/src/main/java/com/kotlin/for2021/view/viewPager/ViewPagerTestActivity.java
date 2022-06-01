package com.kotlin.for2021.view.viewPager;

import android.os.Bundle;
import android.view.View;

import com.kotlin.for2021.R;
import com.kotlin.for2021.TestFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * @author : kingBoy
 * @time 2020/12/24 16:49
 */
public class ViewPagerTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uemng_test);


        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(TestFragment.newInstance());
        fragments.add(TestFragment.newInstance());
        fragments.add(TestFragment.newInstance());
        fragments.add(TestFragment.newInstance());


        ViewPagerUtil.init(this,(ViewPager)findViewById(R.id.view_pager),getSupportFragmentManager(),fragments);

    }

    public void showAFragment(View view) {

    }

    public void showBFragment(View view) {
    }

    public void showCFragment(View view) {


    }
}
