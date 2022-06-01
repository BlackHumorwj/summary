package com.kotlin.for2021.view.event;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kotlin.for2021.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : kingBoy
 * @time 2021/6/2 18:18
 */
public class ViewTest01Activity extends AppCompatActivity {

    private final String TAG = getClass().getName();

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, ViewTest01Activity.class);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_01);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.scrollBy(10,0);
                //mScrollX 的值等于View的左边缘和View内容左边缘在水平方向的距离
            }
        });


    }


}
