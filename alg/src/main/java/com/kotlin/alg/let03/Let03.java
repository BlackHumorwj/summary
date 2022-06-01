package com.kotlin.alg.let03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class Let03 {


    /*
    twoSum
    数组中找出两个数等于目标值target

     一、暴力解法，两层嵌套循环
     二、借助 HashMap 减少循环
     */

    public int[] twoSum(int[] arr, int target) {


        final HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {

            int value = arr[i];

            if (map.containsKey((target - value))) {
                return new int[]{i, map.get((target - value))};
            } else {
                //存入 value ,index
                map.put(value, i);
            }
        }
        return new int[]{-1, -1};
    }


    /*
    threeSum

    找出数组中三数之和为0的全部组合（去重）
    一、暴力解法
    二、排序+双指针
    1、先将数组排序；
    2、一层for循环，过滤重复元素；
    3、确定左右指针；
    4、while 循环 判断左右指针的值是否满足要求
      4.1. 满足要求，记录当前数，索引往中间移动，同时判断去重；
      4.2. 不满足要求，大于则移动右指针，小于则移动左指针；
    */
    public static List<List<Integer>> threeSum(int[] arr) {

        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > 0) {//有序，当前值大于零则后面值都大于零，结束循环
                break;
            }

            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;//值相等
            }

            //确定左右脚标
            int left = i;
            int right = arr.length - 1;

            while (left < right) {

                if (arr[left] + arr[right] + arr[i] == 0) {

                    list.add(Arrays.asList(new Integer[]{arr[i], arr[left], arr[right]}));
                    left++;
                    right--;

                    while (left < right && arr[left] == arr[left - 1]) {
                        left++;
                    }

                    while (left < right && arr[right] == arr[right + 1]) {
                        right--;
                    }

                } else if (arr[left] + arr[right] + arr[i] > 0) {
                    right--;

                } else if (arr[left] + arr[right] + arr[i] < 0) {
                    left++;
                }
            }
        }
        return list;
    }


    /*
    爬楼梯问题
    1、直接使用斐波拉契数列公式；
    2、递归
    3、滚动数组
        3.1 定义三个变量 分别记录 f(n) f(n-1) f(n-2)
        3.2 f(n) = f(n-1)+f(n-2)
        3.3 数组往前移动
     */
    public static int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int o = 1;
        int p = 2;
        int q = 0;

        for (int i = 3; i <= n; i++) {
            q = o + p;
            o = p;
            p = q;
        }
        return q;
    }


    /*
    初级排序 - 选择排序【每次选择最小的数,两两交换】
    1、两重循环，第二层循环确定最小值位置与第一层循环位置进行交换
     */
    public static int[] selectSort(int[] arr) {

        int minIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    minIndex = j;
                }
            }

            //交换位置
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;

        }
        return arr;
    }

    /*
     初级排序 - 插入排序【左边是有序的，每次和左边的数进行比较，进行插入操作】
     1、思想：一个数与左边的数进行比较，使得左边的数是有序的
     */
    public static int[] insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {

            //从当前位置往左进行遍历排序
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    //交换位置
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return arr;
    }


    /*
    初级排序 - 冒泡排序
    1、两层循环，内部两两比较，较大值往右移动
     */

    public static int[] bubbleSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {

            for (int j = 0; j < arr.length - i; j++) {

                if (arr[j] > arr[j + 1]) {
                    //交互
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }
        }
        return arr;
    }


}
