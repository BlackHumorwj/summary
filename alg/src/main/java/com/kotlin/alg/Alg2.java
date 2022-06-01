package com.kotlin.alg;

import java.util.HashMap;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/02/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class Alg2 {
    //这个题目说的是，给你一个整数数组和一个目标值，你要找到数组里两个整数， 它们的和等于目标值。然后返回这两个整数的下标。
    int[] arr = new int[]{1, 4, 2, 5, 8};

    //暴力解法 时间 O(n2) 控件 O(1)
    private int[] fun1(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {

            for (int j = i + 1; j < arr.length; j++) {

                if (arr[i] + arr[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    //最优解 时间 O(n) 控件 O(n)
    private int[] fun2(int[] arr, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {

            int delValue = target - arr[i];

            if (map.get(i) != -1 && (map.containsKey(delValue))) {
                return new int[]{i, map.get(delValue)};
            }
            map.put(arr[i], i);
        }
        return new int[]{-1, -1};
    }

}
