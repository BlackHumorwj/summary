package com.kotlin.lib;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class Thread1 {

    //类锁
    //对象锁
    //显示锁
    //可重入锁


    static class T1 implements Runnable {

        public static int amount;
        static ReentrantLock mReentrantLock = new ReentrantLock();

        @Override
        public void run() {
            mReentrantLock.lock();
            amount++;
            mReentrantLock.unlock();

            //            synchronized (Thread1.class) {
            //
            //                try {
            //                    amount++;
            //                } catch ( Exception e) {
            //                    e.printStackTrace();
            //                }
            //
            //
            //            }

        }
    }


    public static void main(String[] args) throws InterruptedException {
        //        final T1 t1 = new T1();
        //
        //        for (int i = 0; i <= 100; i++) {
        //            new Thread(t1).start();
        //        }
        //
        //        System.out.println(T1.amount);
        //
        //        Thread.sleep(6000);

//        FutureTask;

        final Data data = new Data();

        new Thread(new T2(data)).start();
        new Thread(new T3(data)).start();

        Thread.sleep(6000);
    }


    static class Data {

        public int num;
        public boolean flag;

        public synchronized void add() {
            if (!flag) {
                num++;
                System.out.println("add" + num);
                flag = true;

                notify();//唤醒等待的线程
                try {
                    wait();//等待释放cpu资源
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        public synchronized void minus() {

            if (flag) {
                num--;
                System.out.println("minus" + num);

                flag = false;
                notify();
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    static class T2 implements Runnable {

        private Data data;

        public T2(Data data) {
            this.data = data;
        }


        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                data.add();
            }
        }
    }

    static class T3 implements Runnable {

        private Data data;

        public T3(Data data) {
            this.data = data;
        }


        @Override
        public void run() {

            for (int i = 0; i < 20; i++) {
                data.minus();
            }
        }
    }

}
