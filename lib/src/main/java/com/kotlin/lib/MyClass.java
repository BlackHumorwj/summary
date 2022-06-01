package com.kotlin.lib;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyClass {


    static class WorkThread implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(5000);
            return "hhhhh";
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final WorkThread workThread = new WorkThread();


        //创建 有返回值的异步
        FutureTask<String> futureTask = new FutureTask<>(workThread);

        new Thread(futureTask).start();


        System.out.println(futureTask.get());


        System.out.println("21212");
    }


    //todo 停止线程
    private void stopThread() {
        // 1 stop 不能调用，线程机制里面的来不及释放碎片
        // 2 和谐方式：Run方法执行结束

        final Thread thread = new myThread();
        thread.start();

        thread.interrupt();// 3 发起中断信息

    }

    static class myThread extends Thread {

        @Override
        public void run() {

            //不中断一直运行
            while (!isInterrupted()) {
                System.out.println(Thread.currentThread().getName());
            }

        }
    }


    //todo 如何控制线程顺序执行
    // t1.join() t1优先执行





}