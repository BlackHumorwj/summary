package com.kotlin.for2021;

import android.os.Handler;
import android.os.Message;

import com.kotlin.for2021.util.LogUtil;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class HandlerUtil {


    public void init() {

                Handler handler = new Handler();
                handler.sendMessage(Message.obtain());
        //
        //
        //        Looper.prepare();
        //
        //        IntentService intentService;
        //
        //
        //        new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                synchronized (Handler.class) {
        //
        //
        //                }
        //            }
        //        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    synchronized (HandlerUtil.this) {
                        LogUtil.i(this, "wait before");
                        HandlerUtil.this.wait();
                        LogUtil.i(this, "wait after");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                synchronized (HandlerUtil.this) {
                    try {
                        Thread.sleep(2000);
                        HandlerUtil.this.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        LogUtil.i(this, "wait main");


    }

}
