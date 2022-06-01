package com.kotlin.for2021.java.thread;

import android.util.Log;

import com.kotlin.for2021.util.LogUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : kingBoy
 * @time 2020/12/10 11:26
 */
public class ThreadTest {


    /**
     * 假如有Thread1、Thread2、Thread3、Thread4四条线程分别统计C、D、E、F四个盘的大小，
     * 所有线程都统计完毕交给Thread5线程去做汇总，应当如何实现？
     */

    //1、callable 和 future

    public static class ThreadCount implements Callable<Integer> {


        //Callable<V> 带返回值的任务
        //Runnable 不带返回值的任务

        private int type;

        public ThreadCount(int type) {
            this.type = type;
        }

        @Override
        public Integer call() throws Exception {
            int total = 0;

            LogUtil.i(this, Thread.currentThread().getName());

            switch (type) {
                case 0:
                    LogUtil.i(this, "C盘统计大小");
                    total = 1;
                    break;
                case 1:
                    Thread.sleep(2000);
                    LogUtil.i(this, "D盘统计大小");
                    total = 2;
                    break;
                case 2:
                    LogUtil.i(this, "E盘统计大小");
                    total = 3;
                    break;
                case 3:
                    LogUtil.i(this, "F盘统计大小");
                    total = 4;
                    break;
            }
            return total;
        }
    }


    public static void callableTest() {
        ThreadCount threadCount = null;

        ExecutorService executorService = Executors.newCachedThreadPool();//线程池

        final CompletionService<Integer> cs = new ExecutorCompletionService<>(executorService);

        for (int i = 0; i < 4; i++) {
            threadCount = new ThreadCount(i);
            cs.submit(threadCount);
        }

        // 添加结束，及时shutdown，不然主线程不会结束
        executorService.shutdown();

        int total = 0;
        for (int i = 0; i < 4; i++) {
            try {
                total += cs.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(total);

        /**
         ExecutorCompletionService 是将 Executor和 BlockQueue 结合的jdk类，
         其实现的主要目的是：提交任务线程，每一个线程任务直线完成后，将返回值放在阻塞队列中，然后可以通过阻塞队列的take()方法返回 对应线程的执行结果！！

         BlockQueue? {@link BlockQueueTest} 是个什么鬼
         * 跳转到指定类
         * {@link ThreadTest}
         * 跳转到指定类的指定方法
         * @see  ThreadTest#callableTest(String)

         */


    }


    //2、join阻塞-直接用join把线程5加入进去即可

    /**
     * 由于join()方法的机制是：
     * 当有一个新线程请求加入时，当前线程阻塞，join()内部利用的wait方法，直到新线程执行完毕后，才notifyAll唤醒了主线程，
     * 所以这样当三个线程依次加入后，结果必定是3个线程都已经统计完毕,
     * 但是有个缺点，这有点违背了3个线程并发的初衷，现在成了串行执行
     */

    public static void joinTest() {

        Thread t1 = new Thread(new Worker("thread-1"));
        Thread t2 = new Thread(new Worker("thread-2"));
        Thread t3 = new Thread(new Worker("thread-3"));
        Thread t4 = new Thread(new Worker("thread-4"));


        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        t4.start();
        try {
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public static class Worker implements Runnable {

        private String type;

        private static int totalSize = 0;

        public Worker(String type) {
            this.type = type;
            LogUtil.i(this, Thread.currentThread().getName());
        }

        @Override
        public void run() {

            switch (type) {
                case "thread-1":
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    totalSize = totalSize + 1;
                    break;
                case "thread-2":
                    totalSize = totalSize + 2;

                    break;
                case "thread-3":
                    totalSize = totalSize + 3;
                    break;
                case "thread-4":
                    LogUtil.i(Worker.this, totalSize);
                    break;

            }

        }
    }


    //3、CountDownLatch 类
    //一个线程等待另外N个线程完成某个事情之后才能执行
    //CountDownLatch是计数器，线程完成一个就记一个，就像报数一样，只不过是递减的


    //CountDownLatch和CyclicBarrier区别：
    //1.countDownLatch是一个计数器，线程完成一个记录一个，计数器递减，只能只用一次
    //2.CyclicBarrier的计数器更像一个阀门，需要所有线程都到达，然后继续执行，计数器递增，提供reset功能，可以多次使用


    public static class ThreadUtil implements Runnable {

        private String threadType;
        private CountDownLatch mCountDownLatch;

        public ThreadUtil(String threadType, CountDownLatch countDownLatch) {
            this.threadType = threadType;
            mCountDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            switch (threadType) {
                case "D"://
                    //汇总
                    LogUtil.i(this, Thread.currentThread().getName()+"完毕");
                    break;
                default:
                    LogUtil.i(this, threadType);
                    doWork();
                    break;
            }
            mCountDownLatch.countDown();


        }

        private void doWork() {
            try {
                Thread.sleep(5000);
                LogUtil.i(this, "doWork "+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
                final String stackTraceString = Log.getStackTraceString(e);
            }
        }
    }


    public static void countDownLatchTest() {

        final CountDownLatch countDownLatch = new CountDownLatch(3);//3个线程协同工作

        Runnable r1 = new ThreadUtil("A",countDownLatch);
        Runnable r2 = new ThreadUtil("B",countDownLatch);
        Runnable r3 = new ThreadUtil("C",countDownLatch);
        Runnable r4 = new ThreadUtil("D",countDownLatch);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);

        t1.start();
        t2.start();
        t3.start();

        try {
            countDownLatch.await();////等待所有工人完成工作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t4.start();

    }


}
