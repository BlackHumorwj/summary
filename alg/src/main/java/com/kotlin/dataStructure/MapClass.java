package com.kotlin.dataStructure;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/05/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class MapClass {

    public static void main(String[] args) {

        /*
         * 并发问题三个来源
         * 可见性问题：cpu缓存、内存 -> volatile 关键字解决
         *
         * 原子性问题：执行的代码有个多个片段，不会一次性执行完，会存在原子性问题 -> 加锁
         *
         * 有序性问题：编译器提高代码的执行效率，改变代码执行顺序。单线程中允许是没问题的，多线程时
         * 会出现原子性问题。
         *
         *  如何使用ConcurrentHashMap https://juejin.cn/post/6917884547031891982
         *  1.8之前：Segment->ReentrantLock
         *  1.8之后：CAS + synchronize
         */


        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        //put 和 get是线程安全的
        map.put("2", "1");
        map.get("2");

        //线程不安全，原子性存在问题
        final boolean containsKey = map.containsKey("");

        final String absent = map.putIfAbsent("2", "1");
        if (absent != null) {
            System.out.println("已存在");
            return;
        }

    }

}
