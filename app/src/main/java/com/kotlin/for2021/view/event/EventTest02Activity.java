package com.kotlin.for2021.view.event;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.kotlin.for2021.databinding.ActivityEvent02Binding;
import com.kotlin.for2021.util.LogUtil;
import com.kotlin.for2021.util.ToastUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : kingBoy
 * @time 2021/6/2 18:18
 */
public class EventTest02Activity extends AppCompatActivity {

    private final String TAG = getClass().getName();
    private ActivityEvent02Binding mBinding;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, EventTest02Activity.class);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityEvent02Binding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());


        //setContentView(R.layout.activity_event_02);

        //mBinding.llRoot.setEnabled(false);

//        mBinding.llRoot.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

        mBinding.llRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.Companion.customToast("llRoot点击吗");
            }
        });


        mBinding.tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.Companion.customToast("还没点击吗");
            }
        });

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
}
