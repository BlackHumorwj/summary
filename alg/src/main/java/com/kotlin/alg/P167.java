package com.kotlin.alg;

import java.util.HashMap;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/22
 *     desc   : 有序数组的 两数之和
 *     version: 1.0
 * </pre>
 */

class P167 {

    public void fun(int[] arr, int target) {
        final HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {

            if (map.containsKey(target - arr[i])) {
                System.out.println(i + "" + map.get(target - arr[i]));
                return;
            }
            map.put(arr[i], i);
        }
    }


    //二分查找
    public void fun1(int[] arr, int target) {

        int l = 0;
        int r = arr.length-1;

        while (l<r){


        }


    }


    //双指针
    public static void fun2(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            if (arr[l] + arr[r] < target) {
                l++;
            } else if (arr[l] + arr[r] > target) {
                r--;
            } else {
                System.out.println(l + "--" + r);
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 6, 8, 11};
        fun2(arr, 10);

    }


}
