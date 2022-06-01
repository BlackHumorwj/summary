package com.kotlin.for2021.util;

import android.os.SystemClock;
import android.util.Log;

/**
 * @author : kingBoy
 * @time 2020/12/10 11:58
 */
public class LogUtil {


    public static void i (Object o,Object object){
        Log.i(o.toString()+" --> ",object.toString());
    }

    public static void logTime(String tag, String message) {

        Log.e(tag, message + "-->" + SystemClock.currentThreadTimeMillis());
    }

}
