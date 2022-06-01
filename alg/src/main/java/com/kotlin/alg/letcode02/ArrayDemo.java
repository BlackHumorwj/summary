package com.kotlin.alg.letcode02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/22
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class ArrayDemo {


    //三数之和==0
    /*
    1、暴力解法，三重循环
    2、双指针解法
     */
    private List<List<Integer>> threeSum(int[] arr) {
        //双指针解法
        //1、排序
        //2、确定最左指针 k
        //2.1 最左值是否满足要求
        //3、确定数组左右指针

        List<List<Integer>> list = new ArrayList<>();

        Arrays.sort(arr);
        for (int k = 0; k < arr.length; k++) {

            if (arr[k] > 0) {//有序的，后续值都会大于0
                break;
            }

            //k去重操作
            if (k > 0 && arr[k] == arr[k - 1]) {
                continue;
            }


            int target = -arr[k];

            int left = k + 1;
            int right = arr.length - 1;

            while (left < right) {

                if (arr[left] + arr[right] == target) {
                    list.add(Arrays.asList(arr[k], arr[left], arr[right]));

                    left++;
                    right--;

                    //左去重操作
                    while (left < right && arr[left] == arr[left - 1]) {
                        left++;
                    }

                    //右去重操作
                    while (left < right && arr[right] == arr[right + 1]) {
                        right--;
                    }

                } else if (arr[left] + arr[right] < target) {
                    left++;
                } else if (arr[left] + arr[right] > target) {
                    right--;
                }
            }
        }


        return list;
    }


    //爬楼器问题
    /*
    1、斐波拉契数列
    2、递归
    3、滚动数组
     */

    public static int climbingStairs(int n) {

        if (n < 3) {
            return n;
        }
        int f1 = 1;
        int f2 = 2;
        int f3 = 3;

        for (int i = 3; i <= n; i++) {

            f3 = f1 + f2;//3

            f1 = f2; //2

            f2 = f3; //3

        }
        return f3;
    }


    //装的水最多
    public int mostWater(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        int maxArea = 0;

        for (int i = 0; i < arr.length; i++) {

            //两个柱子最小的
            int minHeight = Math.min(arr[left], arr[right]);

            maxArea = Math.max(maxArea, minHeight * (right - left));


            //移动小的柱子
            if (arr[left] < arr[right]) {
                left++;
            } else {
                right--;
            }

        }
        return maxArea;

    }


    //零移动
    /*
     指针
     */
    private int[] moveZero(int[] arr) {

        int k = 0;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] != 0) {
                //非零元素赋值
                arr[k++] = arr[i];
            }
        }

        for (int j = k; j < arr.length; j++) {
            arr[j] = 0;
        }
        return arr;
    }





}
