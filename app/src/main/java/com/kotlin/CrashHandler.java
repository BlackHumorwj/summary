package com.kotlin;

import android.util.Log;

import com.kotlin.for2021.AppData;

import androidx.annotation.NonNull;

/**
 * @author : kingBoy
 * @time 2021/2/23 17:36
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {


    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private AppData application;

    public CrashHandler(AppData application) {
        // // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.application = application;
    }


    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成
     */
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Log.e(CrashHandler.class.getName(), e.getMessage());
    }
}
