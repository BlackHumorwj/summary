package com.kotlin.for2021.java.thread;

import java.util.concurrent.Callable;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class Thread1 implements Callable<String> {
    @Override
    public String call() throws Exception {

        Thread.sleep(10000);
        return "哈哈";

    }


    public static void main(String[] args) {

        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

        threadLocal.set(12);


        threadLocal.set(14);



    }
}
