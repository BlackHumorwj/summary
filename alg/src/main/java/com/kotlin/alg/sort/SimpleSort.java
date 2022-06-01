package com.kotlin.alg.sort;

import java.util.Arrays;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/03/25
 *     desc   : 时间复杂度 n2的
 *     version: 1.0
 * </pre>
 */

public class SimpleSort {


    //1、选择排序【两两交换】
    /*

    每次筛选出最小值放在左边
    int i =0  与  1 ~ n-1 的数据进行比较，把最小值赋值给 i 的位置

     */
    public static int[] sort1(int[] arr) {
        //1、记录循环中最小值的索引
        int minIndex = 0;
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    //2、将最小值的索引记录下来
                    minIndex = j;
                }
            }
            //3、交换 i 与 minIndex 的位置
            temp = arr[i];

            arr[i] = arr[minIndex];

            arr[minIndex] = temp;
        }
        return arr;

    }

    //2、插入排序
    /*
    从左往右遍历

    int i = 1 开始遍历

    arr[i] 与 arr[i-1] 到 arr[0]进行比较 排序，i左边的是有序的
     */
    public static int[] sort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {

            //从高往低遍历（从右往左遍历，有序的）
            for (int j = i; j > 0; j--) {

                if (arr[j] < arr[j - 1]) {
                    //交换位置
                    int tem = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tem;
                }
            }
        }
        return arr;
    }


    //3、冒泡排序
    /*
    loop数组，两两比较将最大的数放在最后一位

     */

    public static int[] sort3(int[] arr) {

        // 注意 ： i = 1
        for (int i = 1; i < arr.length; i++) {

            for (int j = 0; j < arr.length - i; j++) {//每次遍历，将最大的数放最右边

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));

        return arr;
    }
}
