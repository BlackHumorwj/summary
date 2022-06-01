package com.kotlin.for2021.view.draw;

import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.kotlin.for2021.R;
import com.kotlin.for2021.view.DashboardView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/10/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DrawActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onClick", "onClick  ");
            }
        });

        findViewById(R.id.btn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("onClick", "onTouch  " + event.getAction());
                return false;//不消费事件，onClick会被调用
                //return true; //消费事件，onClick 不会被调用

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            initView();
        }

        DashboardView dashboardView = findViewById(R.id.dashboard);
        dashboardView.postDelayed(new Runnable() {
            @Override
            public void run() {
                dashboardView.setCompleteDegree(50);
            }
        }, 3000);

       // NativeStackBlur.process(null, 0);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        final View view = findViewById(R.id.tv_out_line);
        view.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(), 50);
            }
        });

        //添加背景或者是ImageView的时候失效，添加如下设置
        view.setClipToOutline(true);


    }
}
