package com.kotlin.lib;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class ThreadLocal1 {


    static ThreadLocal<String> sStringThreadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "main";
        }
    };


    public static void main(String[] args) throws InterruptedException {

        sStringThreadLocal.set("main1");

    }


    /**
     *
     *    public void set(T value) {
     *         Thread t = Thread.currentThread();
     *         //通过当前线程拿到 map ,map是Thread的成员变量，一一绑定
     *         ThreadLocalMap map = getMap(t);
     *         if (map != null)
     *             map.set(this, value);
     *         else
     *             createMap(t, value);
     *     }
     *
     */



}
