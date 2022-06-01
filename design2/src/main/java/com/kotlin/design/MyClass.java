package com.kotlin.design;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyClass {


    public static void main(String[] args) {

        Executor executors = new ThreadPoolExecutor(5, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
            AtomicInteger count = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                final Thread thread = new Thread(r);
                thread.setName("哈哈" + count);
                return thread;
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if (!executor.isShutdown()) {
                    executor.getQueue().poll();
                    executor.execute(r);
                }
            }
        });


    }

}