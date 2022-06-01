package com.kotlin.for2021.java.thread;

import android.os.SystemClock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class ExecutorUtil {


    private void init() {

        final ExecutorService executorService = Executors.newCachedThreadPool();

        final Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                SystemClock.sleep(3000);
                return "null";
            }
        });


    }


}
