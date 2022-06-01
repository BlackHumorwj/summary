package com.kotlin.for2021.view.measure;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kotlin.for2021.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : kingBoy
 * @time 2021/6/2 18:18
 */
public class MeasureActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MeasureActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);


        View view = findViewById(R.id.fl);

        view.post(new Runnable() {
            @Override
            public void run() {
                Log.e("xxx", "run");
            }
        });
        Log.e("xxx", "after");

    }


}
