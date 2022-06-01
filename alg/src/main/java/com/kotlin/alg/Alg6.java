package com.kotlin.alg;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class Alg6 {


    //这个题目说的是，给你一个非空的整数数组，这个数组中有一个整数只出现了一次，
    //其它的整数都出现两次，你要找出这个只出现一次的整数。

    /*
    位运算：异或操作 ^
    1、两个相同的数 ^ 操作等于 0
    2、0 ^ x =x ,

    分析：其他整数出现两次，有个出现一次 =》 其他数的异或操作 =0 。


     */

    private int fun1(int[] arr) {

        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result ^= arr[i];
        }
        return result;


    }

    /*
    HashSet 不能重复的；
   */
    private int fun2(int[] arr) {
        Set<Integer> set = new HashSet<Integer>();
        int sum = 0, uniqueSum = 0;
        for (int i = 0; i < arr.length; i++) {
            final int value = arr[i];
            if (!set.contains(value)) {
                set.add(value);
                uniqueSum += value;
            }
            sum += value;
        }
        return 2 * uniqueSum - sum;
    }


}
