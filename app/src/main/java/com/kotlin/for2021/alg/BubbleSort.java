package com.kotlin.for2021.alg;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2021/09/13
 *     desc   : 冒泡排序
 *     version: 1.0
 * </pre>
 */

public class BubbleSort {


    //冒泡排序 相邻两个两两比较
    private void bubbleSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {

            for (int j = 0; j < arr.length - i; j++) {

                int i1 = arr[j];
                int i2 = arr[j + 1];
                if (i1 > i2) {

                    int temp = arr[j];
                    arr[j] = arr[j = 1];
                    arr[j + 1] = temp;

                }
            }
        }
    }


}
