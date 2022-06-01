package com.kotlin.for2021;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : kingBoy
 * @time 2021/6/2 18:18
 */
public class TestActivtiy extends AppCompatActivity {
    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, TestActivtiy.class);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Log.e("xxx", 1 / 0 + "");

    }
}
