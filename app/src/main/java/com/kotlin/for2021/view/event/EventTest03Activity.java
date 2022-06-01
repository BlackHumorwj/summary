package com.kotlin.for2021.view.event;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.kotlin.for2021.R;
import com.kotlin.for2021.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

/**
 * @author : kingBoy
 * @time 2021/6/2 18:18
 */
public class EventTest03Activity extends AppCompatActivity {

    private final String TAG = getClass().getName();

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, EventTest03Activity.class);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_03);

        initView();

    }

    private void initView() {
        MyViewPager viewPager = findViewById(R.id.view_pager);

        ArrayList<ListView> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add((ListView) LayoutInflater.from(this).inflate(R.layout.list_view, null));
        }
        viewPager.setAdapter(new MyPageAdapter(list));

    }

    //顶层分发  false/true 直接消费
    //super 会继续进行分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final boolean dispatchTouchEvent = super.dispatchTouchEvent(ev);
        LogUtil.i(TAG, " dispatchTouchEvent= " + dispatchTouchEvent);

        return dispatchTouchEvent;
    }


    //子View 分发方法返回
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final boolean onTouchEvent = super.onTouchEvent(event);
        LogUtil.i(TAG, " onTouchEvent= " + onTouchEvent);
        return onTouchEvent;
    }


    class MyPageAdapter extends PagerAdapter {

        private ArrayList<ListView> mList;
        private Context mContext;

        public MyPageAdapter(ArrayList<ListView> list) {
            this.mList = list;
        }


        @Override
        public int getCount() {
            return mList.size();
        }

        //判断instantiateItem(ViewGroup, int)函数所返回来的对象与一个页面视图是否是代表的同一个视图(即它俩是否是对应的，对应的表示同一个View),通常我们直接写 return view == object!
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        //移除一个给定位置的View
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mList.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ListView listView = mList.get(position);
            Log.e("instantiateItem", position + "");
            final MyListAdapter adapter = new MyListAdapter();
            listView.setAdapter(adapter);
            container.addView(listView);
            return listView;
        }
    }


    class MyListAdapter extends BaseAdapter {

        private List<String> mList = new ArrayList<>();


        public MyListAdapter() {
            for (int i = 0; i < 20; i++) {
                mList.add("i =" + i);
            }
        }


        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e("getView", position + "");
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom, null);
            return convertView;
        }
    }


}
