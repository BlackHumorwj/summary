package com.kotlin.alg.letcode;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/21
 *     desc   : 70. 爬楼梯
 *     https://leetcode-cn.com/problems/climbing-stairs/
 *     version: 1.0
 * </pre>
 */

public class Let02 {


    //做题只做一遍，5遍刷题法


    /*

    f(1) =1 ;
    f(2) =2 ;

    f(3) = f(1)+f(2) //数学归纳法 if else for while recursion =》 找重复性

    => f(n) = f(n-1)+f(n2)

    斐波拉契数列
     */

    public static void fun1(int n) {

        if (n <= 2) {
            System.out.println("n = " + n);
            return;
        }

        int f1 = 1;
        int f2 = 2;
        int f3 = 3;

        //判断条件 i = 3 ； i <= n
        for (int i = 3; i <= n; i++) {

            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }

        System.out.println("n = " + f3);


    }

}

