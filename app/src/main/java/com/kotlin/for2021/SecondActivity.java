package com.kotlin.for2021;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.kotlin.RxBus;
import com.kotlin.for2021.util.LogUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import io.reactivex.disposables.Disposable;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SecondActivity extends AppCompatActivity {

    private Disposable mDisposable;


    private final String TAG = this.getClass().getSimpleName();

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, SecondActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        setContentView(R.layout.activity_second);
        LogUtil.logTime(TAG, "setContentView");
        mDisposable = ExtKt.getExtData(intent, String.class, new Consumer<String>() {
            @Override
            public void accept(String s) {
                LogUtil.logTime(TAG, "accept");
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, TestFragment.newInstance()).commit();
            }
        });

        LogUtil.logTime(TAG, "setContentView after");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unRegister(mDisposable);
    }



}
